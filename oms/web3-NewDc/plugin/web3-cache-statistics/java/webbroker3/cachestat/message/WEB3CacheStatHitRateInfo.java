head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatHitRateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatHitRatioInfo�N���X(WEB3CacheStatHitRatioInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �L���b�V�����v�E�q�b�g�����
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatHitRateInfo extends Message
{
    
    /**
     * �e�[�u����
     */
    public String tableName;
    
    /**
     * �L���b�V���^�C�v�ibeans, enum, row�j
     */
    public String cacheType;
    
    /**
     * �g���C��
     */
    public String tryCount;
    
    /**
     * �q�b�g��
     */
    public String hitCount;
    
    /**
     * �~�X��
     */
    public String missCount;
    
    /**
     * �q�b�g��
     */
    public String hitRate;
    
    /**
     * �~�X��
     */
    public String missRate;
    
    /**
     * �������Ɏw�肳�ꂽ���ʂȓ��e
     */
    public WEB3CacheStatSpecialCase[] specialCases;

}
@
