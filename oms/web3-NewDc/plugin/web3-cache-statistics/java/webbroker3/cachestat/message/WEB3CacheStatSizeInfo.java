head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatSizeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatisticsSizeInfo�N���X(WEB3CacheStatSizeInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �L���b�V�����v�E�T�C�Y���
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatSizeInfo extends Message
{
    
    /**
     * �e�[�u����
     */
    public String tableName;
    
    /**
     * �L���b�V���^�C�v�irow�Aenum�Ȃ�)
     */
    public String cacheType;
    
    /**
     * �e��
     */
    public String capacity ;
    
    /**
     * ����
     */
    public String averageCount;
    
    /**
     * �ő�
     */
    public String maxCount;
    
    /**
     * �W���΍�
     */
    public String standardDeviation;
    
    /**
     * �g�p��
     */
    public String useRate;

}
@
