head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatGetStatisticsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatGetStatisticsResponse�N���X(WEB3CacheStatGetStatisticsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Response;


/**
 * �L���b�V�����v�擾���X�|���X���b�Z�[�W
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatGetStatisticsResponse extends Response
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "cache_stat_get_statistics";
    
    /**
     * ���N�G�X�g���b�Z�[�W�Őݒ肳�ꂽ���v�����
     * 
     * @@see webbroker3.cachestat.define.WEB3CacheStatType
     */
    public int statType;
    
    /**
     * ���N�G�X�g���b�Z�[�W�Őݒ肳�ꂽ�e�[�u����
     */
    public String tableName;
    
    /**
     * �T�C�Y���
     */
    public WEB3CacheStatSizeInfo[] sizeInfo;
    
    /**
     * �q�b�g�����
     */
    public WEB3CacheStatHitRateInfo[] hitRateInfo;
    
    /**
     * �C���o���f�[�V�������
     */
    public WEB3CacheStatInvInfo[] invInfo;
    
    /**
     * �^�C�~���O���
     */
    public WEB3CacheStatTimingInfo[] timingInfo;
    

}
@
