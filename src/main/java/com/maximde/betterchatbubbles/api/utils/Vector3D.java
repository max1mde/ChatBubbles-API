package com.maximde.betterchatbubbles.api.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vector3D {
    public final float x;
    public final float y;
    public final float z;

    public Vector3D() {
        this.x = 0.0F;
        this.y = 0.0F;
        this.z = 0.0F;
    }

    public Vector3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3D subtract(float x, float y, float z) {
        return new Vector3D(this.x - x, this.y - y, this.z - z);
    }
    public Vector3D subtract(Vector3D other) {
        return this.subtract(other.x, other.y, other.z);
    }
    public Vector3D multiply(float x, float y, float z) {
        return new Vector3D(this.x * x, this.y * y, this.z * z);
    }
    public Vector3D multiply(Vector3D other) {
        return this.multiply(other.x, other.y, other.z);
    }
    public Vector3D multiply(float value) {
        return this.multiply(value, value, value);
    }
    public Vector3D add(float x, float y, float z) {
        return new Vector3D(this.x + x, this.y + y, this.z + z);
    }
    public Vector3D add(Vector3D other) {
        return this.add(other.x, other.y, other.z);
    }
    public static Vector3D zero() {
        return new Vector3D();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Vector3D vec) {
            return this.x == vec.x && this.y == vec.y && this.z == vec.z;
        }
        return false;
    }
}
