head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.34.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiLotteryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��Ғ��I�L�T�[�r�X���׏��ꗗ�s(WEB3AdminSrvRegiLotteryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import java.util.Date;


/**
 * (�T�[�r�X���p�Ǘ��Ғ��I�L�T�[�r�X���׏��ꗗ�s)<BR>
 * �T�[�r�X���p�Ǘ��Ғ��I�L�T�[�r�X���׏��ꗗ�s�f�[�^�N���X<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiLotteryGroup extends WEB3AdminSrvRegiDetailCommon 
{
    
    /**
     * (�^�p�敪)<BR>
     * 0:���������I�@@1:�ʏ�^�p�i���I�L�j�@@2:�ʏ�^�p�i���I�L�I�[�N�V�����j<BR>
     */
    public String useDiv;
    
    /**
     * (�\�����ԁi���j)<BR>
     * �\���J�n��<BR>
     */
    public Date applyStartDate;
    
    /**
     * (�\�����ԁi���j)<BR>
     * �\���I����<BR>
     */
    public Date applyEndDate;
    
    /**
     * (���I��)
     */
    public Date lotteryDate;
    
    /**
     * (�K�p�J�n��)
     */
    public Date trialStartDate;
    
    /**
     * (�K�p�I����)
     */
    public Date trialEndDate;
    
    /**
     * (���p����)<BR>
     * this.�^�p�敪��"���������I"�܂���"�ʏ�^�p�i���I�L�j"�̏ꍇ�A<BR>
     * "���p����"�Ƃ��ĎQ��<BR>
     * this.�^�p�敪��"�ʏ�^�p�i���I�L�I�[�N�V�����j"�̏ꍇ�A<BR>
     * "�Œ���D���i"�Ƃ��ĎQ�Ƃ���B<BR>
     */
    public String chargeAmt;
    
    /**
     * (���D�P��)
     */
    public String biddingPriceUnit;
    
    /**
     * (�T�[�r�X���p�Ǘ��Ғ��I�L�T�[�r�X���׏��ꗗ�s)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F2210201D4
     */
    public WEB3AdminSrvRegiLotteryGroup() 
    {
     
    }
}
@
