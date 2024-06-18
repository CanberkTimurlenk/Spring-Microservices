package org.common.s3;

import java.io.InputStream;

public interface FileStrategy {

    String getName();

    InputStream getInputStream();
}
