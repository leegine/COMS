head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatGetStatisticsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatGetStatisticsRequest�N���X(WEB3CacheStatGetStatisticsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Request;


/**
 * �L���b�V�����v�擾���N�G�X�g���b�Z�[�W
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatGetStatisticsRequest extends Request
{
    
    /** 
     * PTYPE 
     */
    public static final String PTYPE = "cache_stat_get_statistics";
    
    /**
     * �擾���铝�v���̎��
     * 
     * @@see webbroker3.cachestat.define.WEB3CacheStatType
     */
    public int statType;
    
    /**
     * ���v�����擾����e�[�u���̃e�[�u����
     */
    public String tableName;

}
@
