head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatTimingInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatTimingInfo�N���X(WEB3CacheStatTimingInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/20 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �L���b�V�����v�E�^�C�~���O���
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatTimingInfo extends Message
{
    
    /**
     * �e�[�u����
     */
    public String tableName;
    
    /**
     * �L���b�V���^�C�v�ibeans, enumu, row�j
     */
    public String cacheType;
    
    /**
     * Where����e
     */
    public String condition;
    
    /**
     * �ڍׁihit, miss, etc�j
     */
    public String detail;
    
    /**
     * ������
     */
    public String count;
    
    /**
     * ���Ϗ������ԁi�b�j
     */
    public String averageTime;
    
    /**
     * �ő又�����ԁi�b�j
     */
    public String maxTime;
    
    /**
     * �������Ԃ̕W���΍��i�b�j
     */
    public String standardDeviation;
    
    /**
     * �ݐϏ������ԁi�b�j
     */
    public String totalTime;

}
@
