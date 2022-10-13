// SPDX-License-Identifier: MPL-2.0

package com.dxfeed.api;

import com.oracle.svm.core.c.ProjectHeaderFile;
import org.graalvm.nativeimage.c.CContext;
import org.graalvm.nativeimage.c.constant.CEnum;
import org.graalvm.nativeimage.c.constant.CEnumLookup;
import org.graalvm.nativeimage.c.constant.CEnumValue;

import java.util.Collections;
import java.util.List;

@CContext(ErrorNative.NativeDirectives.class)
public final class ErrorNative {
    public static class NativeDirectives implements CContext.Directives {
        @Override
        public List<String> getHeaderFiles() {
            return Collections.singletonList(ProjectHeaderFile.resolve(
                    "com.dxfeed",
                    "src/main/c/dxf_graal_error_codes.h"));
        }
    }

    private ErrorNative() {
        throw new IllegalStateException("Native class");
    }

    @CEnum("dxf_graal_error_code_t")
    public enum ErrorCodes {
        DX_EC_SUCCESS,
        DX_EC_UNKNOWN_ERR,
        DX_EC_NULL_POINTER_EX,
        DX_EC_ILLEGAL_ARGUMENT_EX,
        DX_EC_SECURITY_EX,
        DX_EC_ILLEGAL_STATE_EX,
        DX_EC_UNKNOWN_DESCRIPTOR;

        @CEnumValue
        public native int getCValue();

        @CEnumLookup
        public static native ErrorCodes fromCValue(int value);
    }
}
