// -*- C++ -*-
// Definition for Windows export directives.
// This file is generated automatically
// ------------------------------
#ifndef INCIDENT_EXPORT_H
#define INCIDENT_EXPORT_H

#include <ace/config-all.h>

#if defined (ACE_AS_STATIC_LIBS) && !defined (INCIDENT_HAS_DLL)
#  define INCIDENT_HAS_DLL 0
#endif /* ACE_AS_STATIC_LIBS && INCIDENT_HAS_DLL */

#if !defined (INCIDENT_HAS_DLL)
#  define INCIDENT_HAS_DLL 1
#endif /* ! INCIDENT_HAS_DLL */

#if defined (INCIDENT_HAS_DLL) && (INCIDENT_HAS_DLL == 1)
#  if defined (INCIDENT_BUILD_DLL)
#    define Incident_Export ACE_Proper_Export_Flag
#    define INCIDENT_SINGLETON_DECLARATION(T) ACE_EXPORT_SINGLETON_DECLARATION (T)
#    define INCIDENT_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK) ACE_EXPORT_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK)
#  else /* INCIDENT_BUILD_DLL */
#    define Incident_Export ACE_Proper_Import_Flag
#    define INCIDENT_SINGLETON_DECLARATION(T) ACE_IMPORT_SINGLETON_DECLARATION (T)
#    define INCIDENT_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK) ACE_IMPORT_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK)
#  endif /* INCIDENT_BUILD_DLL */
#else /* INCIDENT_HAS_DLL == 1 */
#  define Incident_Export
#  define INCIDENT_SINGLETON_DECLARATION(T)
#  define INCIDENT_SINGLETON_DECLARE(SINGLETON_TYPE, CLASS, LOCK)
#endif /* INCIDENT_HAS_DLL == 1 */

// Set INCIDENT_NTRACE = 0 to turn on library specific tracing even if
// tracing is turned off for ACE.
#if !defined (INCIDENT_NTRACE)
#  if (ACE_NTRACE == 1)
#    define INCIDENT_NTRACE 1
#  else /* (ACE_NTRACE == 1) */
#    define INCIDENT_NTRACE 0
#  endif /* (ACE_NTRACE == 1) */
#endif /* !INCIDENT_NTRACE */

#if (INCIDENT_NTRACE == 1)
#  define INCIDENT_TRACE(X)
#else /* (INCIDENT_NTRACE == 1) */
#  if !defined (ACE_HAS_TRACE)
#    define ACE_HAS_TRACE
#  endif /* ACE_HAS_TRACE */
#  define INCIDENT_TRACE(X) ACE_TRACE_IMPL(X)
#  include "ace/Trace.h"
#endif /* (INCIDENT_NTRACE == 1) */

#endif /* INCIDENT_EXPORT_H */

// End of auto generated file.