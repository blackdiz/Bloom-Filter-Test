package com.blackdiz;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.BitSet;

public class BloomFilter {

    private BitSet bitSet;
    private int arrayLength;

    public BloomFilter(int arrayLength) {
        this.arrayLength = arrayLength;
        bitSet = new BitSet(arrayLength);
    }

    public void add(String s) {
        // hash 3 times
        for (int i = 0; i < 3; i++) {
            // here use Google Guava's murmur3 hash function, and use i as seed to hash different hash codes
            HashFunction murmur3 = Hashing.murmur3_128(i);
            int hashCode = murmur3.hashUnencodedChars(s).asInt();
            // cause hashCode may be negative, so use Math.abs to get a positive number
            int index = Math.abs(hashCode % arrayLength);
            bitSet.set(index);
        }
    }

    public boolean check(String s) {
        for (int i = 0; i < 3; i++) {
            HashFunction murmur3 = Hashing.murmur3_128(i);
            int hashCode = murmur3.hashUnencodedChars(s).asInt();
            int index = Math.abs(hashCode % arrayLength);
            if (!bitSet.get(index)) {
                return false;
            }
        }

        return true;
    }
}
