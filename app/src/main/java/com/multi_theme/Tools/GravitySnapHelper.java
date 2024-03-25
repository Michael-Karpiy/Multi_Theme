package com.multi_theme.Tools;

import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class GravitySnapHelper extends LinearSnapHelper {

    private OrientationHelper mVerticalHelper;
    private OrientationHelper mHorizontalHelper;
    private final int mGravity;
    private boolean mIsRtlHorizontal;
    private final boolean mSnapLastItemEnabled;
    SnapListener mSnapListener;
    boolean mSnapping;
    private final RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_SETTLING) {
                mSnapping = false;
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE && mSnapping && mSnapListener != null) {
                int position = getSnappedPosition(recyclerView);
                if (position != RecyclerView.NO_POSITION) {
                    mSnapListener.onSnap(position);
                }
                mSnapping = false;
            }
        }
    };

    public GravitySnapHelper(int gravity) {
        this(gravity, false, null);
    }

    public GravitySnapHelper(int gravity, boolean enableSnapLastItem, SnapListener snapListener) {
        if (gravity != Gravity.START && gravity != Gravity.END
                && gravity != Gravity.BOTTOM && gravity != Gravity.TOP) {
            throw new IllegalArgumentException("Invalid gravity value. Use START " +
                    "| END | BOTTOM | TOP constants");
        }
        mSnapListener = snapListener;
        mGravity = gravity;
        mSnapLastItemEnabled = enableSnapLastItem;
    }

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView)
            throws IllegalStateException {
        if (recyclerView != null) {
            if (mGravity == Gravity.START || mGravity == Gravity.END) {
                mIsRtlHorizontal
                        = false;
            }
            if (mSnapListener != null) {
                recyclerView.addOnScrollListener(mScrollListener);
            }
        }
        Objects.requireNonNull(recyclerView).setOnFlingListener(null);
        super.attachToRecyclerView(recyclerView);
    }

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager,
                                              @NonNull View targetView) {
        int[] out = new int[2];

        if (layoutManager.canScrollHorizontally()) {
            if (mGravity == Gravity.START) {
                out[0] = distanceToStart(targetView, getHorizontalHelper(layoutManager), false);
            } else { // END
                out[0] = distanceToEnd(targetView, getHorizontalHelper(layoutManager), false);
            }
        }

        if (layoutManager.canScrollVertically()) {
            if (mGravity == Gravity.TOP) {
                out[1] = distanceToStart(targetView, getVerticalHelper(layoutManager), false);
            } else { // BOTTOM
                out[1] = distanceToEnd(targetView, getVerticalHelper(layoutManager), false);
            }
        }

        return out;
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        View snapView = null;
        if (layoutManager instanceof LinearLayoutManager) {
            snapView = switch (mGravity) {
                case Gravity.START -> findStartView(layoutManager, getHorizontalHelper(layoutManager));
                case Gravity.END -> findEndView(layoutManager, getHorizontalHelper(layoutManager));
                case Gravity.TOP -> findStartView(layoutManager, getVerticalHelper(layoutManager));
                case Gravity.BOTTOM -> findEndView(layoutManager, getVerticalHelper(layoutManager));
                default -> snapView;
            };
        }

        mSnapping = snapView != null;

        return snapView;
    }

    private int distanceToStart(View targetView, OrientationHelper helper, boolean fromEnd) {
        if (mIsRtlHorizontal && !fromEnd) {
            return distanceToEnd(targetView, helper, true);
        }

        return helper.getDecoratedStart(targetView) - helper.getStartAfterPadding();
    }

    private int distanceToEnd(View targetView, OrientationHelper helper, boolean fromStart) {
        if (mIsRtlHorizontal && !fromStart) {
            return distanceToStart(targetView, helper, true);
        }

        return helper.getDecoratedEnd(targetView) - helper.getEndAfterPadding();
    }

    private View findStartView(RecyclerView.LayoutManager layoutManager,
                               OrientationHelper helper) {

        if (layoutManager instanceof LinearLayoutManager) {
            int firstChild = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

            if (firstChild == RecyclerView.NO_POSITION) {
                return null;
            }

            View child = layoutManager.findViewByPosition(firstChild);

            float visibleWidth;

            if (mIsRtlHorizontal) {
                visibleWidth = (float) (helper.getTotalSpace() - helper.getDecoratedStart(child))
                        / helper.getDecoratedMeasurement(child);
            } else {
                visibleWidth = (float) helper.getDecoratedEnd(child)
                        / helper.getDecoratedMeasurement(child);
            }

            boolean endOfList = ((LinearLayoutManager) layoutManager)
                    .findLastCompletelyVisibleItemPosition()
                    == layoutManager.getItemCount() - 1;

            if (visibleWidth > 0.5f && !endOfList) {
                return child;
            } else if (mSnapLastItemEnabled && endOfList) {
                return child;
            } else if (endOfList) {
                return null;
            } else {
                return layoutManager.findViewByPosition(firstChild + 1);
            }
        }

        return null;
    }

    private View findEndView(RecyclerView.LayoutManager layoutManager,
                             OrientationHelper helper) {

        if (layoutManager instanceof LinearLayoutManager) {
            int lastChild = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

            if (lastChild == RecyclerView.NO_POSITION) {
                return null;
            }

            View child = layoutManager.findViewByPosition(lastChild);

            float visibleWidth;

            if (mIsRtlHorizontal) {
                visibleWidth = (float) helper.getDecoratedEnd(child)
                        / helper.getDecoratedMeasurement(child);
            } else {
                visibleWidth = (float) (helper.getTotalSpace() - helper.getDecoratedStart(child))
                        / helper.getDecoratedMeasurement(child);
            }

            boolean startOfList = ((LinearLayoutManager) layoutManager)
                    .findFirstCompletelyVisibleItemPosition() == 0;

            if (visibleWidth > 0.5f && !startOfList) {
                return child;
            } else if (mSnapLastItemEnabled && startOfList) {
                return child;
            } else if (startOfList) {
                return null;
            } else {
                return layoutManager.findViewByPosition(lastChild - 1);
            }
        }
        return null;
    }

    int getSnappedPosition(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

        if (layoutManager instanceof LinearLayoutManager) {
            if (mGravity == Gravity.START || mGravity == Gravity.TOP) {
                return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
            } else if (mGravity == Gravity.END || mGravity == Gravity.BOTTOM) {
                return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
            }
        }

        return RecyclerView.NO_POSITION;

    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mVerticalHelper == null) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return mVerticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }

    public interface SnapListener {
        void onSnap(int position);
    }
}