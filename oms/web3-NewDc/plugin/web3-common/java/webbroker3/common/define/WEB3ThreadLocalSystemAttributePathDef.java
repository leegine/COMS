head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ThreadLocalSystemAttributePathDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �X���b�h���[�J���V�X�e�������ݒ�p�X�@@�萔��`�C���^�t�F�C�X(WEB3ThreadLocalSystemAttributePathDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ���c ���r(SRA) �V�K�쐬
Revesion History : 2007/07/03 �h�C (���u)�y�敨�I�v�V�����z�d�l�ύX�E���f��No.772
Revesion History : 2008/10/28 �h�C (���u)�y���Ǘ��ҁz�d�l�ύX�E���f��No.176
Revesion History : 2008/04/22 �h�C (���u)�y�g���K�[�����z�d�l�ύX�E���f��No.336
Revesion History : 2010/01/25 ��іQ (���u)�y�O�������z�d�l�ύX�E���f��No.536
Revesion History : 2010/03/05 ��іQ (���u)�y�O�������z�d�l�ύX�E���f��No.541
Revesion History : 2010/09/15 ��іQ (���u)�y�O�������z�d�l�ύX�E���f��No.553
*/
package webbroker3.common.define;

/**
 * �X���b�h���[�J���V�X�e�������ݒ�p�X�@@�萔��`�C���^�t�F�C�X
 *
 * @@author  ���c ���r(SRA)
 * @@version 1.0
 */
public interface WEB3ThreadLocalSystemAttributePathDef
{
    /**
     * ThreadLocal�ɕۑ�����X�L�b�v���ꎷ�s�戵��~
     */
    public static final String SKIP_TRIGGER_ORDER_STOP = "web3.skiptriggerorderstop";

    /**
     * RLS�ւ̔񓯊��ʒm�t���O
     */
    public static final String ORDER_CARRYOVER_ASYNC_RLS_NOTIFY = "web3.ordercarryoverasyncrlsnotify";

    /**
     * �������ϒ��������R���X�L�b�v�t���O
     */
    public static final String FORCED_SETTLE_ORDER_VALIDATION_SKIP = "web3.forcedsettleordervalidationskip";

    /**
     * �\�񒍕������t���O
     */
    public static final String SUCC_CHANGE_ORDER = "web3.succchangeorder";

    /**
     * �����בփ��[�g�`�F�b�N�t���O
     */
    public static final String DAY_EXCHANGE_CHECK_FLAG = "web3.dayexchangecheckflag";

    /**
     * �����ב֓o�^�t���O
     */
    public static final String DAY_EXCHANGE_REGISTRATION_FLAG = "web3.dayexchangeregistrationflag";
    
    /**
     * �בփl�b�e�B���O�t���O
     */
    public static final String NETTING_EXCHANGE_FLAG = "web3.nettingexchangeflag";
}@
