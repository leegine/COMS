head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatClearRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatClearRequest�N���X(WEB3CacheStatClearRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/22 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Request;


/**
 * �L���b�V�����v�N���A���N�G�X�g���b�Z�[�W
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatClearRequest extends Request
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "cache_stat_clear";

}
@