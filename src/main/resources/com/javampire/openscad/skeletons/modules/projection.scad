/**
 * Using the projection() function, you can create 2d drawings from 3d models,
 * and export them to the dxf format. It works by projecting a 3D model to the
 * (x,y) plane, with z at 0.
 *
 * @param boolean Default is false. If cut=true, only points with z=0 will be considered (effectively cutting the object), with cut=false(the default), points above and below the plane will be considered as well (creating a proper projection).
 */
module projection(cut = boolean) {}