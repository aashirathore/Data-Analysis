
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

	public class ShowGraphSS extends JPanel{
	    /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Vector<DataAS> mdsList;
		int type;
		String title;
		String yAxisTitle;
	    public ShowGraphSS(Vector<DataAS> mdgList, int type, String title, String yAxisTitle)  {
	        //super("Analysis By Gender");
	        this.mdsList = mdgList;
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
	    		   dataset.addValue(mdsList.get(0).getCount(), mdsList.get(0).getAverageSpendingScore(), mdsList.get(0).getAverageSpendingScore());
	    		   dataset.addValue(mdsList.get(1).getCount(), mdsList.get(1).getAverageSpendingScore(), mdsList.get(0).getAverageSpendingScore());
	    		   dataset.addValue(mdsList.get(2).getCount(), mdsList.get(2).getAverageSpendingScore(), mdsList.get(0).getAverageSpendingScore());
	    		   dataset.addValue(mdsList.get(3).getCount(), mdsList.get(3).getAverageSpendingScore(), mdsList.get(0).getAverageSpendingScore());

	    	   }
	    	  
	    	   else if(type==2){
	    		   dataset.addValue(mdsList.get(0).getTotalAnnualIncome(), mdsList.get(0).getAverageSpendingScore(), mdsList.get(0).getAverageSpendingScore());
	    		   dataset.addValue(mdsList.get(1).getTotalAnnualIncome(), mdsList.get(1).getAverageSpendingScore(), mdsList.get(0).getAverageSpendingScore());
	    		   dataset.addValue(mdsList.get(2).getTotalAnnualIncome(), mdsList.get(2).getAverageSpendingScore(), mdsList.get(0).getAverageSpendingScore());
	    		   dataset.addValue(mdsList.get(3).getTotalAnnualIncome(), mdsList.get(3).getAverageSpendingScore(), mdsList.get(0).getAverageSpendingScore());

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






