/*
 * Encog(tm) Core v2.5 - Java Version
 * http://www.heatonresearch.com/encog/
 * http://code.google.com/p/encog-java/
 
 * Copyright 2008-2010 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */

package org.encog.mathutil.rbf;


/**
 * Multi-dimensional Mexican Hat, or Ricker wavelet, function.
 * 
 * It is usually only referred to as the "Mexican hat" in the Americas, due to
 * cultural association with the "sombrero". In technical nomenclature this function
 * is known as the Ricker wavelet, where it is frequently employed to model
 * seismic data.
 * 
 * http://en.wikipedia.org/wiki/Mexican_Hat_Function
 * 
 */
public class MexicanHatFunction extends BasicRBF {

	/**
	 * Create centered at zero, width 0, and peak 0.
	 */
	public MexicanHatFunction(int dimensions)
	{
		this.setCenters(new double[dimensions]);
		this.setPeak(1.0);
		this.setWidth(1.0);		
	}
	
	/**
	 * Construct a multi-dimension Mexican hat function with the specified peak,
	 * centers and widths.
	 * 
	 * @param peak
	 *            The peak for all dimensions.
	 * @param center
	 *            The centers for each dimension.
	 * @param width
	 *            The widths for each dimension.
	 */
	public MexicanHatFunction(final double peak, final double[] center,
			final double width) {
		this.setCenters( center );
		this.setPeak( peak );
		this.setWidth( width );
	}
	
	/**
	 * Construct a single-dimension Mexican hat function with the specified peak,
	 * centers and widths.
	 * 
	 * @param peak
	 *            The peak for all dimensions.
	 * @param center
	 *            The centers for each dimension.
	 * @param width
	 *            The widths for each dimension.
	 */
	public MexicanHatFunction(final double center, final double peak,
			final double width) {
		this.setCenters(new double[1]);
		this.getCenters()[0] = center;
		this.setPeak(peak);
		this.setWidth(width);
	}


	/**
	 * Calculate the result from the function.
	 * 
	 * @param x
	 *            The parameters for the function, one for each dimension.
	 * @return The result of the function.
	 */
	public double calculate(final double[] x) {
		
		double[] center = getCenters();
		
		// calculate the "norm", but don't take square root
		// don't square because we are just going to square it
		double norm = 0;
		for(int i=0;i<center.length;i++)
		{
			norm+=Math.pow(x[i]-center[i],2);
		}

		// calculate the value
		
		return this.getPeak() * (1-norm)*Math.exp(-norm/2);
	}

}