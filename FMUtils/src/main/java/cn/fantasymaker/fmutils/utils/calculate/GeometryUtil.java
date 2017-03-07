/*
 *  Copyright 2016 Lixplor
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cn.fantasymaker.fmutils.utils.calculate;

import android.graphics.PointF;

/**
 * Created :  2016-07-25
 * Author  :  Lixplor
 * Web     :  http://blog.lixplor.com
 * Email   :  me@lixplor.com
 */
public class GeometryUtil {

    private GeometryUtil() throws IllegalAccessException {
        throw new IllegalAccessException("Instantiation is not allowed! Use static methods only!");
    }

    /**
     * Calculate the value between two values with by percent
     *
     * @param fraction percent
     * @param start    start value
     * @param end      end value
     * @return value of percent
     */
    private static float evaluateValue(float fraction, Number start, Number end) {
        return start.floatValue() + (end.floatValue() - start.floatValue()) * fraction;
    }

    /**
     * Calculate distance between 2 points
     *
     * @param p1 a point
     * @param p2 another point
     * @return distance between 2 points
     */
    public static float getDistanceBetween2Points(PointF p1, PointF p2) {
        return (float) Math.sqrt(Math.pow(p1.y - p2.y, 2) + Math.pow(p1.x - p2.x, 2));
    }

    /**
     * Get middle point between p1 and p2.
     *
     * @param p1 a point
     * @param p2 another point
     * @return middle point object
     */
    public static PointF getMiddlePoint(PointF p1, PointF p2) {
        return new PointF((p1.x + p2.x) / 2.0f, (p1.y + p2.y) / 2.0f);
    }

    /**
     * Get point between p1 and p2 by percent.
     *
     * @param p1      a point
     * @param p2      another point
     * @param percent given percent to calculate target point
     * @return target point in given percent distance
     */
    public static PointF getPointByPercent(PointF p1, PointF p2, float percent) {
        return new PointF(evaluateValue(percent, p1.x, p2.x), evaluateValue(percent, p1.y, p2.y));
    }

    /**
     * Get 2 points by a line which intersection between center of circle and circle with given slope.
     *
     * @param centerPoint The center point of circle
     * @param radius      The circle radius (r)
     * @param slope       The slope of line which cross the pMiddle.
     * @return points of cross
     */
    public static PointF[] getIntersectionPoints(PointF centerPoint, float radius, Double slope) {
        //must have 2 points
        PointF[] points = new PointF[2];
        float radian;
        float xOffset;
        float yOffset;
        if (slope != null) {
            radian = (float) Math.atan(slope);
            xOffset = (float) (Math.cos(radian) * radius);
            yOffset = (float) (Math.sin(radian) * radius);
        } else {
            xOffset = radius;
            yOffset = 0;
        }
        points[0] = new PointF(centerPoint.x + xOffset, centerPoint.y + yOffset);
        points[1] = new PointF(centerPoint.x - xOffset, centerPoint.y - yOffset);
        return points;
    }
}
