head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatStopCollectingRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatStopCollectingRequest�N���X(WEB3CacheStatStopCollectingRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Request;


/**
 * �L���b�V�����v�����W�I�����b�Z�[�W
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatStopCollectingRequest extends Request
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "cache_stat_stop_collecting";

    /**
     * �J�n�O�Ɏ��W�ς݂̓��v�����N���A����ꍇ<code>true</code>
     */
    public boolean clearStat;
    
}
@
