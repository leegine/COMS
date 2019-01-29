head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginChangeCancelHistoryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �M�p���������藚�𖾍׃N���X(WEB3MarginChangeCancelHistoryGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 ������ (���u) �V�K�쐬
Revesion History : 2004/12/10 �K�� (SRA) �C��
Revesion History : 2006/11/02 �����F(���u) ���f�� 948,999
Revesion History : 2007/06/05 �����q (���u) ���f��1164
Revesion History : 2007/07/24 �����q (���u) ���f��1188
*/
package webbroker3.equity.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * �i�M�p���������藚�𖾍ׁj�B<br>
 * <br>
 * �M�p���������藚�𖾍׃N���X
 * @@version 1.0
 */
public class WEB3MarginChangeCancelHistoryGroup extends Message 
{
   
    /**
     * (����No)<BR>
     */
    public String orderActionId;
    
    /**
     * (������t����)<BR>
     */
    public Date orderDate;
    
    /**
     * (�������e�敪)<BR>
     * 00�F�V�K���� 01�F������t 02�F�V�K����(���s) 03�F�������� <BR>
     * 04�F������t 05�F�������� 06�F��������(���s) 07�F������� <BR>
     * 08�F�����t 09�F������� 10�F�������(���s) 11�F�ꕔ��� <BR>
     * 12�F�S����� 13�F����� 14�F���� 15�F�������  <BR>
     * 16�F���� 17�F�����J�z 18�F�����J�z(���s) <BR>
     * 19�F�����(�������n)<BR>
     * 20�F������ 21�F�����x�� <BR>
     * 22�F�ؑ֒x�� 23�F�ؑ֒��� 24�F�ؑ֎�t 25�F�ؑ֊��� <BR>
     * 26�F�ؑ֒���(���s) 27�F�X�g�b�v�������� 29�F�������� 99�F���̑�<BR>
     */
    public String orderType;
    
    /**
     * (�l�i����)<BR>
     * 0:�w��Ȃ��@@1:���ݒl�w�l�@@3:�D��w�l�@@5:���s�c���w�l�@@7:���s�c����� <BR>
     */
    public String priceCondType;
    
    /**
     * (���s����)<BR>
     * 1�F������ 3:��t�@@4:�����@@7:�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (���������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (�t�w�l�p���������P��)<BR>
     * �t�w�l�p���������P��
     */
    public String stopOrderCondPrice;
    
    /**
     * (�t�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ� <BR>
     */
    public String stopOrderCondOperator;
    
    /**
     * (�w�l�p���������P��) <BR>
     * �v�w�l�p���������P��
     */
    public String wlimitOrderCondPrice;
    
    /**
     * (�v�w�l�p�����������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ� <BR>
     */
    public String wlimitOrderCondOperator;
    
    /**
     * (W�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W�w�l�p�����P��) <BR>
     * W�w�l�p�����P��
     */
    public String wLimitPrice;

    /**
     * (�v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * ���ȊO�A���A���������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���B<BR>
     */
    public String wlimitExecCondType;

    /**
     * (�v�w�l�p�L����ԋ敪)<BR>
     * 0�F���~�b�g�����L���@@1�F�X�g�b�v�����L��<BR>
     * 2�F�X�g�b�v����������<BR>
     * ���ȊO�A���A<BR>
     * ���������敪�܂��͌����������敪���A�u2�FW�w�l�v�̏ꍇ�ݒ肳���B<BR>
     */
    public String wlimitEnableStatusDiv;

    /**
     * (�v�w�l�p�֑ؑO�����P��)<BR>
     * ���ȊO�A���A<BR>
     * ���������敪�܂��͌����������敪���u2�FW�w�l�v�A����<BR>
     * W�w�l�̗L����Ԃ�"�X�g�b�v�����L��"�i���ؑ֊����j�̏ꍇ�A�ݒ肳���B<BR>
     */
    public String wlimitBefChgLimitPrice;

    /**
     * (�v�w�l�p�֑ؑO���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * <BR>
     * ���ȊO�A���A<BR>
     * ���������敪�܂��͌����������敪���u2�FW�w�l�v�A����<BR>
     * W�w�l�̗L����Ԃ�"�X�g�b�v�����L��"�i���ؑ֊����j�̏ꍇ�A�ݒ肳���B<BR>
     */
    public String wlimitBefChgExecCondType;

    /**
     * (�����������敪)<BR>
     * 0�F�w��Ȃ��@@1�F�t�w�l�@@2�FW�w�l<BR>
     * <BR>
     * �������������s��̏ꍇ�ɐݒ�<BR>
     */
    public String orgOrderCondType;

    /**
     * (�����������P��)<BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgOrderCondPrice;

    /**
     * (�������������Z�q)<BR>
     * 1�F�ȏ�@@2�F�ȉ�<BR>
     * �������������敪���A1�F�t�w�l�A2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgOrderCondOperator;

    /**
     * (���v�w�l�p�����P���敪)<BR>
     * 0�F���s�@@1�F�w�l<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitOrderPriceDiv;

    /**
     * (���v�w�l�p�����P��)<BR>
     * �����v�w�l�p�����P���敪���A1�F�w�l�̏ꍇ�ݒ肳���<BR>
     */
    public String orgWlimitPrice;

    /**
     * (���v�w�l�p���s����)<BR>
     * 1�F������ 3�F��t 4�F���� 7�F�s�o���������s<BR>
     * �������������敪��2�F�v�w�l�̏ꍇ<BR>
     */
    public String orgWlimitExecCondType; 

    /**
     * (��������)<BR>
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * <BR>
     * 0�F���s�@@�@@1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     */
    public String limitPrice;
    
    /**
     * (��芔��)<BR>
     */
    public String execQuantity;
    
    /**
     * (���P��)<BR>
     */
    public String execPrice;
    
    /**
     * (�����L������)<BR>
     * ����<BR>
     * <BR>
     * YYYY/MM/DD�`���ŕ\��<BR>
     */
    public Date expirationDate;
    
    /**
     * (��t���ʋ敪)<BR>
     * 0000�F���� 1001�F��t�G���[ 1002�F�����G���[ 1003�F����G���[ 1004�F�ؑփG���[<BR>
     * 0001�F�l���G���[ 0002�F�a����s���G���[ <BR>
     * 0003�F�����c���s���G���[ 0004�F�ۏ؋��s���G���[ <BR>
     * 0005�F�����c���s���G���[ 0006�F������~�����G���[ <BR>
     * 0007�F�s��ύX�����G���[ 0008�F���t�]�̓G���[ 
     * 0009�F���t�\���ʃG���[ 0010�F��������G���[ 0011�F�����J�z�X�L�b�v�����G���[ <BR>
     * 0012�F��K���`�F�b�N�G���[�@@0014�F�Ēl�`�F�b�N�G���[<BR>
     * 0015�F�󔄂�`�F�b�N�G���[�@@0016�F���ϊ��������σG���[<BR>
     * 9001�F���̑��G���[<BR>
     */
    public String acceptedResultDiv;
    
    /**
     * @@roseuid 414047D8011E
     */
    public WEB3MarginChangeCancelHistoryGroup() 
    {
     
    }
}
@
