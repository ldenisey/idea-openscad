import * as THREE from "three";
import { OrbitControls } from "three/examples/jsm/controls/OrbitControls.js";
import { STLLoader } from "three/examples/jsm/loaders/STLLoader.js";

const NAME_MODEL = "SCAD_model";
const NAME_GRID = "GRID";
const NAME_AXIS = "AXIS";
const NAME_CAMERA = "CAMERA";
const GRID_COLOR = 0x909090;
const MODEL_COLOR = 0xfef353;

if (typeof window.cefQuery !== "function") {
  window.cefQuery = console.log;
}

window.cefQuery({ request: "Reloading from scratch" });

function getModelBoxSize() {
  const mesh = scene.getObjectByName(NAME_MODEL);
  if (!mesh.boxSize) {
    let box3Size = new THREE.Vector3();
    new THREE.Box3().setFromObject(mesh).getSize(box3Size);
    mesh.boxSize = box3Size;
  }
  return mesh.boxSize;
}

function getModelSize() {
  const mesh = scene.getObjectByName(NAME_MODEL);
  if (!mesh.size) {
    const modelBoxSize = getModelBoxSize();
    mesh.size = Math.max(modelBoxSize.x, modelBoxSize.y, modelBoxSize.z) * 1.5;
    if (mesh.size <= 0) {
      let height = isNaN(window.innerHeight)
        ? window.clientHeight
        : window.innerHeight;
      let width = isNaN(window.innerWidth)
        ? window.clientWidth
        : window.innerWidth;
      mesh.size = Math.min(height, width) * 0.8;
    }
  }
  return mesh.size;
}

// Axis
function showAxis(enabled) {
  scene.remove(scene.getObjectByName(NAME_AXIS));
  if (enabled) {
    const axisHelper = new THREE.AxesHelper(getModelSize() / 2);
    axisHelper.name = NAME_AXIS;
    axisHelper.rotation.x = -Math.PI / 2;
    scene.add(axisHelper);
  }
  window.cefQuery({ request: "showAxis=" + enabled });
}

// Add grid
function showGrid(enabled) {
  scene.remove(scene.getObjectByName(NAME_GRID));
  if (enabled) {
    let divisions = 10;

    const gridXY = new THREE.GridHelper(
      getModelSize(),
      divisions,
      new THREE.Color(GRID_COLOR),
      new THREE.Color(GRID_COLOR),
    );
    gridXY.name = NAME_GRID;
    scene.add(gridXY);
  }
  window.cefQuery({ request: "showGrid=" + enabled });
}

// Model color
function setModelColor(color) {
  const mesh = scene.getObjectByName(NAME_MODEL);
  mesh.material.color.setHex(color);
  window.cefQuery({ request: "modelColor=0x" + color.toString(16) });
}

function saveConfiguration() {
  const mesh = scene.getObjectByName(NAME_MODEL);
  sessionStorage.setItem("color", mesh.material.color.getHex().toString());
  sessionStorage.setItem(
    "showAxis",
    (typeof scene.getObjectByName(NAME_AXIS) === "object").toString(),
  );
  sessionStorage.setItem(
    "showGrid",
    (typeof scene.getObjectByName(NAME_GRID) === "object").toString(),
  );
  sessionStorage.setItem("position", camera.position.toArray());
  sessionStorage.setItem("quaternion", camera.quaternion.toArray());
}

function loadConfiguration() {
  window.cefQuery({ request: "Load configuration" });
  let color = sessionStorage.getItem("color");
  if (color === null) {
    color = MODEL_COLOR;
    sessionStorage.setItem("color", color.toString());
  } else {
    color = parseFloat(color);
  }
  setModelColor(color);

  let isShowAxis = sessionStorage.getItem("showAxis");
  if (isShowAxis === null) {
    isShowAxis = true;
    sessionStorage.setItem("showAxis", isShowAxis.toString());
  } else {
    isShowAxis = isShowAxis === "true";
  }
  showAxis(isShowAxis);

  let isShowGrid = sessionStorage.getItem("showGrid");
  if (isShowGrid === null) {
    isShowGrid = true;
    sessionStorage.setItem("showGrid", isShowGrid.toString());
  } else {
    isShowGrid = isShowGrid === "true";
  }
  showGrid(isShowGrid);

  let position = sessionStorage.getItem("position");
  if (position === null) {
    const size = getModelSize() / 2;
    position = new THREE.Vector3(size / 2, size, size);
  } else {
    const parsed = position.split(",");
    position = new THREE.Vector3(
      parseFloat(parsed[0]),
      parseFloat(parsed[1]),
      parseFloat(parsed[2]),
    );
  }
  const camera = scene.getObjectByName(NAME_CAMERA);
  camera.position.copy(position);

  let quaternion = sessionStorage.getItem("quaternion");
  if (quaternion === null) {
    quaternion = new THREE.Quaternion(0, 0, 0, 0);
  } else {
    const parsed = quaternion.split(",");
    quaternion = new THREE.Quaternion(
      parseFloat(parsed[0]),
      parseFloat(parsed[1]),
      parseFloat(parsed[2]),
      parseFloat(parsed[3]),
    );
  }
  camera.quaternion.copy(quaternion);

  controls.update();
}

// Render scene
function render() {
  renderer.render(scene, camera);
}

function animate() {
  requestAnimationFrame(animate);
  render();
}

// Expose functions
window.showAxis = showAxis;
window.showGrid = showGrid;
window.setModelColor = setModelColor;
window.saveConfiguration = saveConfiguration;
window.loadConfiguration = loadConfiguration;

// Get model
const model = document.getElementsByName("model")[0].content || "demo.stl";

// Create scene
const scene = new THREE.Scene();
scene.background = null;

// Create camera
const camera = new THREE.PerspectiveCamera(
  75,
  window.innerWidth / window.innerHeight,
  0.1,
  1000,
);
camera.name = NAME_CAMERA;

// Create lights
const ambientLight = new THREE.AmbientLight(0xffffff, 1);
scene.add(ambientLight);
const pointLight = new THREE.PointLight(0xffffff, 1500, 0, 2);
camera.add(pointLight);
scene.add(camera);

// Create render
const renderer = new THREE.WebGLRenderer({ alpha: true });
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

// Create controls
const controls = new OrbitControls(camera, renderer.domElement);
controls.enableDamping = true;

// Create material
const material = new THREE.MeshStandardMaterial({
  color: 0x000000,
  metalness: 0,
  roughness: 0.2,
  flatShading: true,
});

// Load STL
const loader = new STLLoader();
loader.load(
  model,
  function (geometry) {
    const mesh = new THREE.Mesh(geometry, material);
    mesh.name = NAME_MODEL;
    mesh.rotation.x = -Math.PI / 2;
    mesh.geometry.center();
    scene.add(mesh);
    loadConfiguration();
  },
  (xhr) => {
    console.log((xhr.loaded / xhr.total) * 100 + "% loaded");
  },
  (error) => {
    console.log(error);
  },
);

window.addEventListener(
  "resize",
  function () {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
    render();
  },
  false,
);

animate();
