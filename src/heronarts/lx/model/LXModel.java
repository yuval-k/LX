/**
 * ##library.name##
 * ##library.sentence##
 * ##library.url##
 *
 * Copyright ##copyright## ##author##
 * All Rights Reserved
 * 
 * @author      ##author##
 * @modified    ##date##
 * @version     ##library.prettyVersion## (##library.version##)
 */

package heronarts.lx.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An LXModel is a representation of a set of points in 3-d space. Each LXPoint
 * corresponds to a single point. Models are comprised of Fixtures. An LXFixture
 * specifies a set of points, the Model object takes some number of these and wraps
 * them up with a few useful additional fields, such as the center position of all
 * points and the min/max/range on each axis.
 */
public class LXModel implements LXFixture {

	/**
	 * An immutable list of all the points in this model
	 */
	public final List<LXPoint> points;
	
	/**
	 * An immutable list of all the fixtures in this model
	 */
	public final List<LXFixture> fixtures;
	
	/**
	 * Center of the model in x space
	 */
	public final float cx;
	
	/**
	 * Center of the model in y space
	 */
	public final float cy;
	
	/**
	 * Center of the model in z space
	 */
	public final float cz;
	
	/**
	 * Minimum x value
	 */
	public final float xMin;
	
	/**
	 * Maximum x value
	 */
	public final float xMax;
	
	/**
	 * Range of x values
	 */
	public final float xRange;
	
	/**
	 * Minimum y value
	 */
	public final float yMin;
	
	/**
	 * Maximum y value
	 */
	public final float yMax;
	
	/**
	 * Range of y values
	 */
	public final float yRange;
	
	/**
	 * Minimum z value
	 */
	public final float zMin;
	
	/**
	 * Maximum z value
	 */
	public final float zMax;
	
	/**
	 * Range of z values
	 */
	public final float zRange;
	
	/**
	 * Constructs a null model with no points
	 */
	public LXModel() {
		this(new LXFixture[] {});
	}

	/**
	 * Constructs a model from a list of points
	 * 
	 * @param points Points
	 */
	public LXModel(List<LXPoint> points) {
		this(new BasicFixture(points));
	}
	
	/**
	 * Constructs a model with one fixture
	 * 
	 * @param fixture Fixture
	 */
	public LXModel(LXFixture fixture) {
		this(new LXFixture[] { fixture });
	}
		
	/**
	 * Constructs a model with the given fixtures
	 * 
	 * @param fixtures Fixtures
	 */
	public LXModel(LXFixture[] fixtures) {
		List<LXFixture> _fixtures = new ArrayList<LXFixture>();
		List<LXPoint> _points = new ArrayList<LXPoint>();
		for (LXFixture fixture : fixtures) {
			_fixtures.add(fixture);
			for (LXPoint point : fixture.getPoints()) {
				_points.add(point);
			}
		}
		
		this.points = Collections.unmodifiableList(_points);
		this.fixtures = Collections.unmodifiableList(_fixtures);
		
		float ax = 0, ay = 0, az = 0;
		float _xMin = 0, _xMax = 0, _yMin = 0, _yMax = 0, _zMin = 0, _zMax = 0;
		
		boolean firstPoint = true;
		for (LXPoint p : this.points) {
			ax += p.x;
			ay += p.y;
			az += p.z;
			if (firstPoint) {
				_xMin = _xMax = p.x;
				_yMin = _yMax = p.y;
				_zMin = _zMax = p.z;
			} else {
				if (p.x < _xMin) _xMin = p.x;
				if (p.x > _xMax) _xMax = p.x;
				if (p.y < _yMin) _yMin = p.y;
				if (p.y > _yMax) _yMax = p.y;
				if (p.z < _zMin) _zMin = p.z;
				if (p.z > _zMax) _zMax = p.z;
			}
			firstPoint = false;
		}
		this.cx = ax / Math.max(1, this.points.size());
		this.cy = ay / Math.max(1, this.points.size());
		this.cz = az / Math.max(1, this.points.size());
		this.xMin = _xMin;
		this.xMax = _xMax;
		this.xRange = _xMax - _xMin;
		this.yMin = _yMin;
		this.yMax = _yMax;
		this.yRange = _yMax - _yMin;
		this.zMin = _zMin;
		this.zMax = _zMax;
		this.zRange = _zMax - _zMin;
	}
	
	public List<LXPoint> getPoints() {
		return this.points;
	}
	
	private final static class BasicFixture implements LXFixture {
		private final List<LXPoint> points;
		
		private BasicFixture(List<LXPoint> points) {
			this.points = points;
		}
		
		public List<LXPoint> getPoints() {
			return this.points;
		}
	}
	
}