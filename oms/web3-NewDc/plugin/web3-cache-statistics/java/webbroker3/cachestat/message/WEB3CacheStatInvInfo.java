head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatInvInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3CacheStatInvalidationInfoUnit�N���X(WEB3CacheStatInvalidationInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �L���b�V�����v�E�C���o���f�[�V�������
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatInvInfo extends Message
{
    
    /**
     * �e�[�u����
     */
    public String tableName;
    
    /**
     * �����^�C�v�iins, upd, del�j
     */
    public String mutatorType;
    
    /**
     * �e�[�u�����̌���
     */
    public String count;
    
    /**
     * �e�[�u�����̕��Ϗ������ԁi�b�j
     */
    public String averageTime;
    
    /**
     * �e�[�u�����̍ő又�����ԁi�b�j
     */
    public String maxTime;
    
    /**
     * �e�[�u�����̕W���΍��i�b�j
     */
    public String standardDeviation;
    
    /**
     * �e�[�u�����̗ݐϏ������ԁi�b�j
     */
    public String totalTime;

}
@
