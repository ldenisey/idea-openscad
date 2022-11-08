/**
 * Offset allows moving 2D outlines outward or inward by a given amount.
 *
 * This is useful for making thin walls, by differencing a positive-offset
 * exterior and a negative-offset interior.
 *
 * Fillet: offset(r=-3) offset(delta=+3) rounds all inside (concave) corners,
 * and leaves flat walls unchanged. However, holes less than 2*r in diameter
 * will vanish.
 *
 * Round: offset(r=+3) offset(delta=-3) rounds all outside (convex) corners,
 * and leaves flat walls unchanged. However, walls less than 2*r thick will
 * vanish.
 *
 * Example 1:
 *
 *     linear_extrude(height = 60, twist = 90, slices = 60) {
 *        difference() {
 *          offset(r = 10) {
 *           square(20, center = true);
 *          }
 *          offset(r = 8) {
 *            square(20, center = true);
 *          }
 *        }
 *     }
 *
 * Example 2:
 *
 *     module fillet(r) {
 *        offset(r = -r) {
 *          offset(delta = r) {
 *            children();
 *          }
 *        }
 *     }
 *
 * @param number_r Double. Amount to offset the polygon. When negative, the polygon is offset inwards. The parameter r specifies the radius that is used to generate rounded corners.
 * @param number_d Double. Amount to offset the polygon. When negative, the polygon is offset inwards. The parameter delta gives straight edges.
 */
module offset(number_r, number_d) {}