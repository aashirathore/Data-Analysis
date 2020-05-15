
	/*
	 * To change this template, choose Tools | Templates
	 * and open the template in the editor.
	 */

	import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.Vector;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

	public class ShowGraph extends JPanel{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Vector<DataByGender> dbgList;
		int type;
		String title;
		String yAxisTitle;
	    public ShowGraph(Vector<DataByGender> dbgList, int type, String title, String yAxisTitle)  {
	        //super("Analysis By Gender");
	        this.dbgList = dbgList;
	        this.type = type;
	        this.title = title;
	        this.yAxisTitle = yAxisTitle;
	        final CategoryDataset dataset = createDataset();
	        final JFreeChart chart = createChart(dataset);
	        final ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new Dimension(550, 300));
	        add(chartPanel);

	    }
	    private CategoryDataset createDataset() {
	        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	       try {
	    	   if(type == 1) {
	    		   dataset.addValue(dbgList.get(0).getCount(), dbgList.get(0).getGender(), dbgList.get(0).getGender());
	    		   dataset.addValue(dbgList.get(1).getCount(), dbgList.get(1).getGender(), dbgList.get(0).getGender());
	    	   }
	    	   else if(type==2){
	    		   dataset.addValue(dbgList.get(0).getTotalAnnualIncome(), dbgList.get(0).getGender(), dbgList.get(0).getGender());
	    		   dataset.addValue(dbgList.get(1).getTotalAnnualIncome(), dbgList.get(1).getGender(), dbgList.get(0).getGender());
			   }
	    	   else if(type==3){
	    		   dataset.addValue(dbgList.get(0).getAverageSpendingScore(), dbgList.get(0).getGender(), dbgList.get(0).getGender());
	    		   dataset.addValue(dbgList.get(1).getAverageSpendingScore(), dbgList.get(1).getGender(), dbgList.get(0).getGender());
			   }
	       }
	       catch(Exception ex) {
	           System.out.println(ex.toString());
	       }
	        return dataset;
	    }
	    
	    private JFreeChart createChart(final CategoryDataset dataset) {
	        final JFreeChart chart = ChartFactory.createBarChart(
	            title,
	                                      // chart title
	            "Classifier",       // domain axis label
	            yAxisTitle,        // range axis label
	            dataset,                  // data
	            PlotOrientation.VERTICAL, // orientation
	            true,                     // include legend
	            false,                     // tooltips?
	            false                     // URLs?
	        );
	        chart.setBackgroundPaint(Color.white);
	        final CategoryPlot plot = chart.getCategoryPlot();
	        plot.setBackgroundPaint(Color.lightGray);
	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);
	        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
	        renderer.setDrawBarOutline(false);
	        final GradientPaint gp0 = new GradientPaint(
	            0.0f, 0.0f, Color.orange, 
	            0.0f, 0.0f, Color.lightGray
	        );
	        final GradientPaint gp1 = new GradientPaint(
	            0.0f, 0.0f, Color.magenta, 
	            0.0f, 0.0f, Color.lightGray
	        );
	        renderer.setSeriesPaint(0, gp0);
	        renderer.setSeriesPaint(1, gp1);

	        final CategoryAxis domainAxis = plot.getDomainAxis();
	        domainAxis.setCategoryLabelPositions(
	            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
	        );
	        return chart;
	    }

	    public static void main(final String[] args) {
	        //final ShowGraph demo = new ShowGraph("Bar Chart");
	        //demo.pack();
	        //RefineryUtilities.centerFrameOnScreen(demo);
	        //demo.setVisible(true);
	    }
	}


