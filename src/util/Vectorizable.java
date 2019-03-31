package util;

import transforms.Mat4;

public interface Vectorizable <V>{
    V mul(double t);
    V mul(Mat4 mat4);
    V add(V a);
}
