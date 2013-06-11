package pack.bi.activities;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.SeriesSelection;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import pack.bi.R;
import pack.bi.model.Category;
import pack.bi.model.Product;
import pack.bi.storage.DatabaseHandler;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class StatisticsActivity extends Activity {
    public static final String TYPE = "type";

    public static final String CATOGORY_KEY = "selected_category_key";

    private static int[] COLORS = new int[] { Color.GREEN, Color.BLUE, Color.MAGENTA, Color.CYAN, Color.YELLOW };

    private CategorySeries mSeries = new CategorySeries("");

    private DefaultRenderer mRenderer = new DefaultRenderer();

    private String mDateFormat;

    private GraphicalView mChartView;
    private Category selectedCategory;
    List<Product> productsInCategory;

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        mSeries = (CategorySeries) savedState.getSerializable("current_series");
        mRenderer = (DefaultRenderer) savedState.getSerializable("current_renderer");
        mDateFormat = savedState.getString("date_format");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("current_series", mSeries);
        outState.putSerializable("current_renderer", mRenderer);
        outState.putString("date_format", mDateFormat);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_chart);
        selectedCategory = (Category) getIntent().getExtras().getSerializable(CATOGORY_KEY);
        setTitle(selectedCategory.get_categoryName());
        DatabaseHandler handler = new DatabaseHandler(this);
        productsInCategory = handler.getProductsByCategoryId(selectedCategory.get_idCategory());
        drawGraph();
    }

    private void drawGraph() {
        mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.argb(100, 50, 50, 50));
        mRenderer.setChartTitleTextSize(20);
        mRenderer.setLabelsTextSize(15);
        mRenderer.setLegendTextSize(15);
        mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
        mRenderer.setZoomButtonsVisible(true);
        mRenderer.setStartAngle(90);
        for (Product p : productsInCategory) {
            mSeries.add(p.get_name(), p.get_price());
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
            renderer.setColor(COLORS[(mSeries.getItemCount() - 1) % COLORS.length]);
            mRenderer.addSeriesRenderer(renderer);
        }
        if (mChartView != null) {
            mChartView.repaint();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mChartView == null) {
            LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
            mChartView = ChartFactory.getPieChartView(this, mSeries, mRenderer);
            mRenderer.setClickEnabled(true);
            mRenderer.setSelectableBuffer(10);
            mChartView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
                    if (seriesSelection == null) {
                        Toast.makeText(StatisticsActivity.this, "No chart element was clicked", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(StatisticsActivity.this, productsInCategory.get(seriesSelection.getPointIndex()).get_name(),
                                        Toast.LENGTH_SHORT).show();
                    }
                }
            });
            mChartView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View v) {
                    SeriesSelection seriesSelection = mChartView.getCurrentSeriesAndPoint();
                    if (seriesSelection == null) {
                        Toast.makeText(StatisticsActivity.this, "No chart element was long pressed", Toast.LENGTH_SHORT).show();
                        return false; // no chart element was long pressed, so
                                      // let something
                        // else handle the event
                    } else {
                        Toast.makeText(StatisticsActivity.this, productsInCategory.get(seriesSelection.getPointIndex()).get_name(),
                                        Toast.LENGTH_SHORT).show();
                        return true; // the element was long pressed - the event
                                     // has been
                        // handled
                    }
                }
            });
            layout.addView(mChartView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        } else {
            mChartView.repaint();
        }
    }
}
