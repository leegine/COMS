head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.42.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3SuccReservationOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �\�񒍕�����(WEB3SuccReservationOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/17 ������(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;


/**
 * (�\�񒍕�����)<BR>
 * �\�񒍕�����<BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3SuccReservationOrderUnit extends WEB3SuccOrderUnit 
{
    
    /**
     * (�P�������l)<BR>
     * �P�������l<BR>
     * <BR>
     * ���A�������̒����P���Ƃ��Ė��P����<BR>
     * �@@�g�p����ꍇ�A���̖��P����<BR>
     * �@@������v���X(�}�C�i�X)���邩�̒l���Z�b�g�B<BR>
     */
    public String priceAdjustmentValue = null;
    
    /**
     * (�e�����̒���ID)<BR>
     * �e�����̒���ID<BR>
     */
    public String parentOrderId;
    
    /**
     * (�e�������A��)<BR>
     * �e�������A��<BR>
     */
    public String parentOrderSequentialNo;
    
    /**
     * (�����G���[���R�R�[�h)<BR>
     * �����G���[���R�R�[�h<BR>
     * �i�A�����������Ŏ��s�����ꍇ�̂݃Z�b�g�����j<BR>
     */
    public String orderErrorCode;
        
    /**
     * (�\�񒍕�����)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 431F84BD0004
     */
    public WEB3SuccReservationOrderUnit() 
    {
     
    }
}
@
