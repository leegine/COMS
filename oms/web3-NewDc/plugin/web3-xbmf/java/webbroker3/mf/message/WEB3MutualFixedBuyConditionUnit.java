head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyConditionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z���t�����s(WEB3MutualFixedBuyConditionUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/26 ���G�� (���u) �V�K�쐬
                 : 2006/07/22 ���G�� (���u) �d�l�ύX ���f�� 460
Revesion History : 2008/07/08 ���u�� (���u) �d�l�ύX ���f��No.604,610
*/
package webbroker3.mf.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���M�莞��z���t�����s)<BR>
 * ���M�莞��z���t�����s<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyConditionUnit extends Message  
{
    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String mutualProductCode;
  
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String mutualProductName;
 
    /**
     * (���M�����J�e�S���[�R�[�h)<BR>
     * ���M�����J�e�S���[�R�[�h<BR>
     */
    public String categoryCode;
 
    /**
     * (���t���z(���X))<BR>
     * ���t���z(���X)<BR>
     */
    public String monthlyBuyAmount;
  
    /**
     * (���t���z(�ςݑ���))<BR>
     * ���t���z(�ςݑ���)<BR>
     */
    public String increaseBuyAmount;
  
    /**
     * (�����\������)<BR>
     * �����\������<BR> 
     * ��\���@@AP�Ń\�[�g�Ɏg�p����B<BR>
     */
    public String displayOrder;
   
    /**
     * (�K�p�J�n�N��)<BR>
     * �K�p�J�n�N��<BR> 
     */
    public Date validStartDate;    
    
    /**
     * (�X�V����)<BR>
     * �X�V����<BR>
     */
    public Date updateDate;
    
    /**
     * (���������N��)<BR>
     * ���������N��<BR>
     */
    public Date debitAccountYM;

    /**
     * (�m��������z�i�ςݑ����j)<BR>
     * �m��������z�i�ςݑ����j<BR>
     */
    public String definiteIncreaseBuyAmount;

    /**
     * (�ژ_�����{���`�F�b�N)<BR>
     * �ژ_�����{���`�F�b�N<BR>
     */
    public String checkResult;

    /**
     * (�ꎞ��~���t���O)<BR>
     * �ꎞ��~���t���O<BR>
     */
    public boolean suspensionFlag;

    /**
     * (sonar���M�`�F�b�N)<BR>
     * sonar���M�`�F�b�N<BR>
     */
    public String sonarSendCheck;

    /**
     * (���M�莞��z���t�����s�̃C���X�^���X�𐶐�����B)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyConditionUnit()
    {
    }
}




@
