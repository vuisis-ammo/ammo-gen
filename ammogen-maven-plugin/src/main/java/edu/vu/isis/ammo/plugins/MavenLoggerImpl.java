package edu.vu.isis.ammo.plugins;

import org.apache.maven.plugin.logging.Log;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.helpers.MessageFormatter;


public class MavenLoggerImpl implements Logger {

	private final Log log;
	private final String name;
	
	public MavenLoggerImpl(final Log log, final String name) {
		this.log = log;
		this.name = name;
	}
	

	@Override
	public String getName() {
		return this.name;
	}

	
	/**
	 * debug 
	 */
	@Override
	public void debug(String format) {
		this.log.debug(format);
	}

	@Override
	public void debug(String format, Object arg1) {
		this.log.debug(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void debug(String format, Object... arg1) {
		this.log.debug(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void debug(String format, Throwable ex) {
		this.log.debug(format, ex);
	}

	@Override
	public void debug(Marker marker, String format) {
		this.log.debug(format);
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		this.log.debug(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void debug(Marker marker, String format, Object arg1) {
		this.log.debug(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void debug(Marker marker, String format, Object... arg1) {
		this.log.debug(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void debug(Marker marker, String format, Throwable ex) {
		this.log.debug(format, ex);
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		this.log.debug(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void error(String format) {
		this.log.error(format);
	}

	@Override
	public void error(String format, Object arg1) {
		this.log.error(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void error(String format, Object... arg1) {
		this.log.error(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void error(String format, Throwable ex) {
		this.log.error(format, ex);
	}

	@Override
	public void error(Marker marker, String format) {
		this.log.error(format);
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		this.log.error(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void error(Marker marker, String format, Object arg1) {
		this.log.error(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void error(Marker marker, String format, Object... arg1) {
		this.log.error(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void error(Marker marker, String format, Throwable ex) {
		this.log.error(format, ex);
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		this.log.error(MessageFormatter.format(format, arg1, arg2).getMessage());
	}


	/**
	 * info 
	 */
	@Override
	public void info(String format) {
		this.log.info(format);
	}

	@Override
	public void info(String format, Object arg1) {
		this.log.info(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void info(String format, Object... arg1) {
		this.log.info(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void info(String format, Throwable ex) {
		this.log.info(format, ex);
	}

	@Override
	public void info(Marker marker, String format) {
		this.log.info(format);
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		this.log.info(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void info(Marker marker, String format, Object arg1) {
		this.log.info(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void info(Marker marker, String format, Object... arg1) {
		this.log.info(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void info(Marker marker, String format, Throwable ex) {
		this.log.info(format, ex);
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		this.log.info(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public boolean isDebugEnabled() {
		return true;
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		return true;
	}

	@Override
	public boolean isErrorEnabled() {
		return true;
	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		return true;
	}

	@Override
	public boolean isInfoEnabled() {
		return true;
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		return true;
	}

	@Override
	public boolean isTraceEnabled() {
		return true;
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		return true;
	}

	@Override
	public boolean isWarnEnabled() {
		return true;
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		return true;
	}

	/**
	 * warn 
	 */
	@Override
	public void warn(String format) {
		this.log.warn(format);
	}

	@Override
	public void warn(String format, Object arg1) {
		this.log.warn(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void warn(String format, Object... arg1) {
		this.log.warn(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void warn(String format, Throwable ex) {
		this.log.warn(format, ex);
	}

	@Override
	public void warn(Marker marker, String format) {
		this.log.warn(format);
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		this.log.warn(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void warn(Marker marker, String format, Object arg1) {
		this.log.warn(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void warn(Marker marker, String format, Object... arg1) {
		this.log.warn(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void warn(Marker marker, String format, Throwable ex) {
		this.log.warn(format, ex);
	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		this.log.warn(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	/**
	 * trace 
	 */
	@Override
	public void trace(String format) {
		this.log.debug(format);
	}

	@Override
	public void trace(String format, Object arg1) {
		this.log.debug(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void trace(String format, Object... arg1) {
		this.log.debug(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void trace(String format, Throwable ex) {
		this.log.debug(format, ex);
	}

	@Override
	public void trace(Marker marker, String format) {
		this.log.debug(format);
	}

	@Override
	public void trace(String format, Object arg1, Object arg2) {
		this.log.debug(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

	@Override
	public void trace(Marker marker, String format, Object arg1) {
		this.log.debug(MessageFormatter.format(format, arg1).getMessage());
	}

	@Override
	public void trace(Marker marker, String format, Object... arg1) {
		this.log.debug(MessageFormatter.arrayFormat(format, arg1).getMessage());
	}

	@Override
	public void trace(Marker marker, String format, Throwable ex) {
		this.log.debug(format, ex);
	}

	@Override
	public void trace(Marker marker, String format, Object arg1, Object arg2) {
		this.log.debug(MessageFormatter.format(format, arg1, arg2).getMessage());
	}

}
