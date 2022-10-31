/**
 * A polyhedron is the most general 3D primitive solid. It can be used to
 * create any regular or irregular shape including those with concave as
 * well as convex features. Curved surfaces are approximated by a series
 * of flat surfaces.
 *
 *     // before 2014.03:
 *     polyhedron(
 *         points = [ [X0, Y0, Z0], [X1, Y1, Z1], ... ],
 *         triangles = [ [P0, P1, P2], ... ],
 *         convexity = N
 *     );
 *     // 2014.03 & later:
 *     polyhedron(
 *         points = [ [X0, Y0, Z0], [X1, Y1, Z1], ... ],
 *         faces = [ [P0, P1, P2, P3, ...], ... ],
 *         convexity = N
 *     );
 *
 * Default values:
 *     polyhedron(); yields: polyhedron(points = undef, faces = undef, convexity = 1);
 *
 * All faces must have points ordered in the same direction . OpenSCAD prefers
 * clockwise when looking at each face from outside inwards. The back is viewed
 * from the back, the bottom from the bottom, etc..
 *
 * Example 1 - using polyhedron to generate cube( [ 10, 7, 5 ] ):
 *
 *     CubePoints = [
 *       [  0,  0,  0 ],  //0
 *       [ 10,  0,  0 ],  //1
 *       [ 10,  7,  0 ],  //2
 *       [  0,  7,  0 ],  //3
 *       [  0,  0,  5 ],  //4
 *       [ 10,  0,  5 ],  //5
 *       [ 10,  7,  5 ],  //6
 *       [  0,  7,  5 ]]; //7
 *
 *     CubeFaces = [
 *       [0,1,2,3],  // bottom
 *       [4,5,1,0],  // front
 *       [7,6,5,4],  // top
 *       [5,6,2,1],  // right
 *       [6,7,3,2],  // back
 *       [7,4,0,3]]; // left
 *
 *     polyhedron( CubePoints, CubeFaces );
 *
 * Equivalent descriptions of the bottom face:
 *       [0,1,2,3],
 *       [0,1,2,3,0],
 *       [1,2,3,0],
 *       [2,3,0,1],
 *       [3,0,1,2],
 *       [0,1,2],[2,3,0],   // 2 triangles with no overlap
 *       [1,2,3],[3,0,1],
 *       [1,2,3],[0,1,3],
 *
 * Example 2 - a square base pyramid:
 *
 *     polyhedron(
 *       points=[ [10,10,0],[10,-10,0],[-10,-10,0],[-10,10,0], // the four points at base
 *                [0,0,10]  ],                                 // the apex point
 *       faces=[ [0,1,4],[1,2,4],[2,3,4],[3,0,4],              // each triangle side
 *                   [1,0,3],[2,1,3] ]                         // two triangles for square base
 *     );
 *
 * Example 3 - a triangular prism:
 *
 *     module prism(l, w, h){
 *         polyhedron(
 *                 points=[[0,0,0], [l,0,0], [l,w,0], [0,w,0], [0,w,h], [l,w,h]],
 *                 faces=[[0,1,2,3],[5,4,3,2],[0,4,5,1],[0,3,4],[5,2,1]]
 *                 );
 *
 *         // preview unfolded (do not include in your function
 *         z = 0.08;
 *         separation = 2;
 *         border = .2;
 *         translate([0,w+separation,0])
 *             cube([l,w,z]);
 *         translate([0,w+separation+w+border,0])
 *             cube([l,h,z]);
 *         translate([0,w+separation+w+border+h+border,0])
 *             cube([l,sqrt(w*w+h*h),z]);
 *         translate([l+border,w+separation+w+border+h+border,0])
 *             polyhedron(
 *                 points=[[0,0,0],[h,0,0],[0,sqrt(w*w+h*h),0], [0,0,z],[h,0,z],[0,sqrt(w*w+h*h),z]],
 *                 faces=[[0,1,2], [3,5,4], [0,3,4,1], [1,4,5,2], [2,5,3,0]]
 *             );
 *         translate([0-border,w+separation+w+border+h+border,0])
 *             polyhedron(
 *                 points=[[0,0,0],[0-h,0,0],[0,sqrt(w*w+h*h),0], [0,0,z],[0-h,0,z],[0,sqrt(w*w+h*h),z]],
 *                 faces=[[1,0,2],[5,3,4],[0,1,4,3],[1,2,5,4],[2,0,3,5]]
 *             );
 *         }
 *
 *     prism(10, 5, 3);
 *
 * Debugging polyhedra
 *
 * Mistakes in defining polyhedra include not having all faces with the same order,
 * overlap of faces and missing faces or portions of faces. As a general rule, the
 * polyhedron faces should also satisfy (manifold conditions):
 *
 *     * exactly two faces should meet at any polyhedron edge.
 *
 *     * if two faces have a vertex in common, they should be in
 *       the same cycle face-edge around the vertex.
 *
 * The first rule eliminates polyhedron like two cubes with a common edge and not
 * watertight models; the second excludes polyhedron like two cubes with a common
 * vertex.
 *
 * When viewed from the outside, the points describing each face must be in the same
 * order . OpenSCAD prefers CW, and provides a mechanism for detecting CCW. When the
 * thrown together view (F12) is used with F5, CCW faces are shown in pink. Reorder
 * the points for incorrect faces. Rotate the object to view all faces. The pink view
 * can be turned off with F10.
 *
 * OpenSCAD allows, temporarily, commenting out part of the face descriptions so that
 * only the remaining faces are displayed. Use single line comment to comment out the rest of the line.
 * Use C-style comment to start and end a comment block. This can be part of a line or extend
 * over several lines. Viewing only part of the faces can be helpful in determining the
 * right points for an individual face. Note that a solid is not shown, only the faces.
 * If using F12, all faces have one pink side. Commenting some faces helps also to show
 * any internal face.
 *
 * Example - showing only 2 faces:
 *
 *     CubeFaces = [
 *     // [0,1,2,3],  // bottom
 *     // [4,5,1,0],  // front
 *        [7,6,5,4],  // top
 *     // [5,6,2,1],  // right
 *     // [6,7,3,2],  // back
 *        [7,4,0,3]]; // left
 *
 * After defining a polyhedron, its preview may seem correct. The polyhedron alone may
 * even render fine. However to be sure it is a valid manifold and that it will generate
 * a valid STL file, union it with any cube and render it (F6). If the polyhedron disappears,
 * it means that it is not correct. Revise the winding order of all faces and the two rules
 * stated above.
 *
 * Example 4 - a more complex polyhedron with mis-ordered faces:
 *
 *     // Bad polyhedron
 *     polyhedron(
 *         points = [
 *             [0, -10, 60], [0, 10, 60], [0, 10, 0], [0, -10, 0], [60, -10, 60], [60, 10, 60],
 *             [10, -10, 50], [10, 10, 50], [10, 10, 30], [10, -10, 30], [30, -10, 50], [30, 10, 50]
 *         ],
 *         faces = [
 *               [0,2,3], [0,1,2], [0,4,5], [0,5,1], [5,4,2], [2,4,3],
 *               [6,8,9], [6,7,8], [6,10,11], [6,11,7], [10,8,11],
 *               [10,9,8], [0,3,9], [9,0,6], [10,6, 0], [0,4,10],
 *               [3,9,10], [3,10,4], [1,7,11], [1,11,5], [1,7,8],
 *               [1,8,2], [2,8,11], [2,11,5]
 *          ]
 *     );
 *
 * When you select 'Thrown together' from the view menu and compile the design
 * (not compile and render!) you will see a preview with the mis-oriented polygons
 * highlighted. Unfortunately this highlighting is not possible in the OpenCSG
 * preview mode because it would interfere with the way the OpenCSG preview mode
 * is implemented.)
 *
 * A correct polyhedron would be the following:
 *
 *     polyhedron(
 *         points = [
 *             [0, -10, 60], [0, 10, 60], [0, 10, 0], [0, -10, 0], [60, -10, 60], [60, 10, 60],
 *             [10, -10, 50], [10, 10, 50], [10, 10, 30], [10, -10, 30], [30, -10, 50], [30, 10, 50]
 *         ],
 *         faces = [
 *             [0,3,2], [0,2,1], [4,0,5], [5,0,1], [5,2,4], [4,2,3],
 *             [6,8,9], [6,7,8], [6,10,11],[6,11,7], [10,8,11],
 *             [10,9,8], [3,0,9], [9,0,6], [10,6, 0], [0,4,10],
 *             [3,9,10], [3,10,4], [1,7,11], [1,11,5], [1,8,7],
 *             [2,8,1], [8,2,11], [5,11,2]
 *         ]
 *     );
 *
 * Beginner's tip:
 *     If you don't really understand "orientation", try to identify the mis-oriented
 *     pink faces and then invert the sequence of the references to the points vectors
 *     until you get it right. E.g. in the above example, the third triangle ([0,4,5])
 *     was wrong and we fixed it as [4,0,5]. Remember that a face list is a circular
 *     list. In addition, you may select "Show Edges" from the "View Menu", print a
 *     screen capture and number both the points and the faces. In our example, the
 *     points are annotated in black and the faces in blue. Turn the object around
 *     and make a second copy from the back if needed. This way you can keep track.
 *
 * Clockwise Technique:
 *     Orientation is determined by clockwise circular indexing. This means that if
 *     you're looking at the triangle (in this case [4,0,5]) from the outside you'll
 *     see that the path is clockwise around the center of the face. The winding order
 *     [4,0,5] is clockwise and therefore good. The winding order [0,4,5] is
 *     counter-clockwise and therefore bad. Likewise, any other clockwise order of
 *     [4,0,5] works: [5,4,0] & [0,5,4] are good too. If you use the clockwise technique,
 *     you'll always have your faces outside (outside of OpenSCAD, other programs do use
 *     counter-clockwise as the outside though).
 *
 * Think of it as a Left Hand Rule:
 *     If you hold the face and the fingers of your right hand curls is the same order
 *     as the points, then your thumb points outwards.
 *
 * Succinct description of a 'Polyhedron'
 *     * Points define all of the points/vertices in the shape.
 *     * Faces is a list of flat polygons that connect up the points/vertices.
 *
 * Each point, in the point list, is defined with a 3-tuple x,y,z position specification.
 * Points in the point list are automatically enumerated starting from zero for use in
 * the faces list (0,1,2,3,... etc).
 *
 * Each face, in the faces list, is defined by selecting 3 or more of the points
 * (using the point order number) out of the point list.
 *
 * e.g. faces=[ [0,1,2] ] defines a triangle from the first point
 * (points are zero referenced) to the second point and then to the third point.
 *
 * When looking at any face from the outside, the face must list all points in a
 * clockwise order.
 *
 * Alternate Face Descriptions
 *
 * Before 2014.03, faces could only be described via triangles. Since 2014.03, a
 * face description can have any number of points. The points, all in the same plane,
 * must be listed in the proper order. Since version ???, the face vertices do not
 * have to be planar: OpenSCAD will do its best to internally subdivide the face in
 * triangles. Note that this may lead to different results depending on the chosen
 * face triangulation. If a specific result is needed, the non planar face should be
 * broken in triangular pieces by the user.
 *
 * An alternate (correct) face definition for Example 4:
 *
 *     faces = [
 *       [0,3,2,1], [0,1,5,4],   [2,3,4,5],    // outside
 *       [6,7,8,9], [7,6,10,11], [11,10,9,8],  // inside
 *       [0,4,3,0,6,9,10,6],                   // front
 *       [1,2,5,1,7,11,8,7]                    // back
 *     ]
 *
 * Point repetitions in a polyhedron point list
 *
 * The point list of the polyhedron definition may have repetitions.
 * When two or more points have the same coordinates they are considered
 * the same polyhedron vertex. So, the following polyhedron:
 *
 *     points = [[ 0, 0, 0], [10, 0, 0], [ 0,10, 0],
 *               [ 0, 0, 0], [10, 0, 0], [ 0,10, 0],
 *               [ 0,10, 0], [10, 0, 0], [ 0, 0,10],
 *               [ 0, 0, 0], [ 0, 0,10], [10, 0, 0],
 *               [ 0, 0, 0], [ 0, 0,10], [ 0,10, 0]];
 *     polyhedron(points, [[0,1,2], [3,4,5], [6,7,8], [9,10,11]] );
 *
 * define the same tetrahedron as:
 *
 *     points = [[0,0,0], [0,10,0], [10,0,0], [0,0,10]];
 *     polyhedron(points, [[0,2,1], [0,1,3], [1,2,3], [0,3,2]] );
 *
 * @param vector3 Default is undef. Vector of 3d points or vertices. Each point is in turn a vector, [x,y,z], of its coordinates. Points may be defined in any order. N points are referenced, in the order defined, as 0 to N-1.
 * @param vector_t Default is undef. Vector of faces which collectively enclose the solid. Each face is a vector containing the indices (0 based) of 3 points from the
 *  points vector. (deprecated in version 2014.03, use faces)
 * @param vector_f Default is undef. Vector of faces which collectively enclose the solid. Each face is a vector containing the indices (0 based) of 3 or more points from the points vector. Faces may be defined in any order. Define enough faces to fully enclose the solid, with no overlap.
 *  Points which describe a single face must all be on the same plane. (introduced in version 2014.03)
 * @param number Integer, default is 1. The convexity parameter specifies the maximum number of
 *  faces a ray intersecting the object might penetrate. This parameter is only needed for correctly displaying the object in OpenCSG preview
 *  mode. It has no effect on the polyhedron rendering. For display problems, setting it to 10 should work fine for most cases.
 */
module polyhedron(points = vector3, faces = vector_f, triangles = vector_t, convexity = number){}