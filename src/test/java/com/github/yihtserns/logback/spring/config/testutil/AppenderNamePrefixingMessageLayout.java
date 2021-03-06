/*
 * Copyright 2015 yihtserns.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.yihtserns.logback.spring.config.testutil;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.joran.spi.NoAutoStart;
import ch.qos.logback.core.spi.LifeCycle;
import ch.qos.logback.core.status.Status;

/**
 * @author yihtserns
 * @see #doLayout(ILoggingEvent)
 */
public class AppenderNamePrefixingMessageLayout implements Layout<ILoggingEvent> {

    public static final AppenderNamePrefixingMessageLayout NO_PREFIX = new AppenderNamePrefixingMessageLayout();
    private String prefix = "";
    private String suffix = "";
    private SuffixCreator suffixCreator;
    private Appender parent;

    @Override
    public String doLayout(ILoggingEvent event) {
        return prefix + event.getFormattedMessage() + suffix;
    }

    @Override
    public void start() {
        this.prefix = "[" + parent.getName() + "] ";

        if (suffixCreator != null) {
            suffixCreator.setAppender(parent);
            suffixCreator.start();
            this.suffix = suffixCreator.get();
        }
    }

    public void setParent(Appender parent) {
        this.parent = parent;
    }

    public Appender getParent() {
        return parent;
    }

    public void setSuffixCreator(SuffixCreator suffixCreator) {
        this.suffixCreator = suffixCreator;
    }

    @Override
    public void stop() {
    }

    @Override
    public void setContext(Context context) {
    }

    public String getFileHeader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPresentationHeader() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPresentationFooter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getFileFooter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getContentType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Context getContext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addStatus(Status status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addInfo(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addInfo(String msg, Throwable ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addWarn(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addWarn(String msg, Throwable ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addError(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addError(String msg, Throwable ex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isStarted() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @NoAutoStart
    public static final class SuffixCreator implements LifeCycle {

        private String value = "";
        private Appender appender;

        public String get() {
            return value;
        }

        public void setAppender(Appender appender) {
            this.appender = appender;
        }

        @Override
        public void start() {
            value = " (" + appender.getName() + ")";
        }

        @Override
        public void stop() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isStarted() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
