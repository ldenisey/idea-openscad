package com.javampire.openscad.settings;

import junit.framework.TestCase;

import java.util.Arrays;

public class OpenSCADInfoTest extends TestCase {

    public void testParseStringInfo() {
        OpenSCADInfo.reset();
        OpenSCADInfo.setInfoString("OpenSCAD Version: 2021.01\n" +
                "User Agent: OpenSCAD/2021.01 (Windows(TM) 10.0 SP 0.0 NTW 1 MSDN 724833)\n" +
                "Compiler: GCC \"7.4.0\" 64bit\n" +
                "MinGW build: MingW64\n" +
                "Debug build: No\n" +
                "Boost version: 1_60\n" +
                "Eigen version: 3.3.7\n" +
                "CGAL version, kernels: 4.11, Cartesian<Gmpq>, Extended_cartesian<Gmpq>, Epeck\n" +
                "OpenCSG version: OpenCSG 1.4.2\n" +
                "Qt version: 5.13.0\n" +
                "QScintilla version: 2.11.2\n" +
                "InputDrivers: \n" +
                "lodepng version: 20180910\n" +
                "freetype version: 2.10.1\n" +
                "Application Path: C:/Program Files/OpenSCAD\n" +
                "Documents Path: C:\\Documents\n" +
                "Resource Path: C:/Program Files/OpenSCAD\n" +
                "User Library Path: C:/Documents/OpenSCAD/libraries\n" +
                "Backup Path: C:/Documents/OpenSCAD/backups\n" +
                "OPENSCADPATH: <not set>\n" +
                "OpenSCAD library path:\n" +
                "  C:/Documents/OpenSCAD/libraries\n" +
                "  C:/Program Files/OpenSCAD\\libraries\n" +
                "\n" +
                "OPENSCAD_FONT_PATH: <not set>\n" +
                "OpenSCAD font path:\n" +
                "  C:/WINDOWS/fonts\n" +
                "  C:/.local/share/fonts\n" +
                "  C:/.fonts\n" +
                "\n" +
                "\n" +
                "GLEW version: 1.12.0\n" +
                "OpenGL Version: 4.6.0 - Build 26.20.100.7985\n" +
                "GL Renderer: Intel(R) UHD Graphics\n" +
                "GL Vendor: Intel\n" +
                "RGBA(8888), depth(24), stencil(8)\n" +
                "GL_ARB_framebuffer_object: yes\n" +
                "GL_EXT_framebuffer_object: yes\n" +
                "GL_EXT_packed_depth_stencil: yes\n" +
                "GL context creator: WGL\n" +
                "PNG generator: lodepng\n" +
                "OS info: Microsoft(TM) Windows(TM) 10 0 22000 0x360ecc4 amd64\n" +
                "Machine: 8664\n");

        assertEquals(34, OpenSCADInfo.getInfoKeys().size());
        assertEquals("2021.01", OpenSCADInfo.getOpenSCADVersion());
        assertEquals("C:/Program Files/OpenSCAD", OpenSCADInfo.getApplicationPath());
        assertEquals(Arrays.asList("C:/Documents/OpenSCAD/libraries", "C:/Program Files/OpenSCAD\\libraries"), OpenSCADInfo.getLibraryPaths());
        assertEquals("4.6.0 - Build 26.20.100.7985", OpenSCADInfo.getStringInfo("OpenGL Version"));
        assertEquals("Intel\nRGBA(8888), depth(24), stencil(8)", OpenSCADInfo.getStringInfo("GL Vendor"));
        assertEquals(Arrays.asList("C:/WINDOWS/fonts", "C:/.local/share/fonts", "C:/.fonts"), OpenSCADInfo.getListInfo("OpenSCAD font path"));
        OpenSCADInfo.reset();
    }
}