head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqChangeCancelHistoryGroup.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O������������藚�𖾍�(WEB3FeqChangeCancelHistoryGroup)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 ���� (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[
Revesion History : 2008/10/02 ����(SRA) �y�O�������z�d�l�ύX�Ǘ��䒠�i���f���jNo.471
*/

package webbroker3.feq.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�O������������藚�𖾍�)<BR>
 * �O������������藚�𖾍׃N���X<BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public class WEB3FeqChangeCancelHistoryGroup extends Message 
{
    
    /**
     * (��������ID)<BR>
     * ��������ID<BR>
     */
    public String orderActionId;
    
    /**
     * (��t����)<BR>
     * ��t����<BR>
     */
    public Date orderDate;
    
    /**
     * (������ԋ敪)<BR>
     * ������ԋ敪<BR>
     * <BR>
     * 00�F�V�K����<BR>
     * 01�F������t<BR>
     * 02�F�V�K����(���s)<BR>
     * 03�F��������<BR>
     * 04�F������t<BR>
     * 05�F��������<BR>
     * 06�F��������(���s)<BR>
     * 07�F�������<BR>
     * 08�F�����t<BR>
     * 09�F�������<BR>
     * 10�F�������(���s)<BR>
     * 11�F�ꕔ���<BR>
     * 12�F�S�����<BR>
     * 13�F�����<BR>
     * 14�F����<BR>
     * 15�F�������<BR>
     * 16�F����<BR>
     * 17�F�����J�z<BR>
     * 18�F�����J�z(���s)<BR>
     * 19�F�����(�������n)<BR>
     * 20�F������<BR>
     * 21�F�����x��<BR>
     * 22�F�ؑ֒x��<BR>
     * 23�F�ؑ֒���<BR>
     * 24�F�ؑ֎�t<BR>
     * 25�F�ؑ֊���<BR>
     * 26�F�ؑ֒���(���s)<BR>
     * 27�F�X�g�b�v��������<BR>
     * 28�F������t���<BR>
     * 29�F��������<BR>
     * 30�F���F��<BR>
     * 31�F��菈�����i�ꕔ���j<BR>
     * 32�F��菈�����i�S�����j<BR>
     * 99�F���̑�<BR>
     */
    public String orderType;
    
    /**
     * (���s����)<BR>
     * ���s����<BR>
     * <BR>
     * 1�F�����Ȃ�<BR>
     * 3�F��t<BR>
     * 4�F����<BR>
     * 7�F�s�o���������s<BR>
     */
    public String execCondType;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     * <BR>
     * 0�F�w��Ȃ�<BR>
     * 1�F�t�w�l<BR>
     * 2�FW�w�l<BR>
     */
    public String orderCondType;
    
    /**
     * (���������P��)<BR>
     * ���������P��<BR>
     * <BR>
     * �������������h�t�w�l�h�������́hW�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String orderCondPrice;
    
    /**
     * (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * <BR>
     * 1�F�ȏ�<BR>
     * 2�F�ȉ�<BR>
     * <BR>
     * �������������h�t�w�l�h�������́hW�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String condOperator;
    
    /**
     * (W�w�l�p�����P���敪)<BR>
     * W�w�l�p�����P���敪<BR>
     * <BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     * <BR>
     * �������������hW�w�l�h�̏ꍇ�A�ݒ�<BR>
     */
    public String wLimitOrderPriceDiv;
    
    /**
     * (W�w�l�p�����P��)<BR>
     * W�w�l�p�����P��<BR>
     * <BR>
     * ��W�w�l�p�����P���敪���h�w�l�h�̏ꍇ�A�ݒ�B<BR>
     */
    public String wLimitPrice;
    
    /**
     * (��������)<BR>
     * ��������<BR>
     */
    public String orderQuantity;
    
    /**
     * (�����P���敪)<BR>
     * �����P���敪<BR>
     * <BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     */
    public String orderPriceDiv;
    
    /**
     * (�����P��)<BR>
     * �����P��<BR>
     * <BR>
     * �������P���敪���h�w�l�h�̏ꍇ�A�ݒ�B<BR>
     */
    public String limitPrice;
    
    /**
     * (��萔��)<BR>
     * ��萔��<BR>
     */
    public String execQuantity;
    
    /**
     * (���P��)<BR>
     * ���P��<BR>
     */
    public String execPrice;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public Date executionTimestamp;
    
    /**
     * (�����L������)<BR>
     * �����L������<BR>
     * <BR>
     * �����������敪���h�o����܂Œ����h�̏ꍇ�A�ݒ�<BR>
     */
    public Date expirationDate;
    
    /**
     * (��t���ʋ敪)<BR>
     * ��t���ʋ敪<BR>
     * <BR>
     * 0000�F����<BR>
     * 1001�F��t�G���[<BR>
     * 1002�F�����G���[<BR>
     * 1003�F����G���[<BR>
     * 0001�F�l���G���[<BR>
     * 0002�F�a����s���G���[<BR>
     * 0003�F�c���s���G���[<BR>
     * 0006�F������~�����G���[<BR>
     * 0008�F���t�]�̓G���[<BR>
     * 0009�F���t�\���ʃG���[<BR>
     * 0010�F��������G���[<BR>
     * 0011�F�����J�z�X�L�b�v�����G���[<BR>
     * 0012�F�O�ݕs���G���[<BR>
     * 9001�F���̑��G���[<BR>
     */
    public String acceptedResultDiv;
    
    /**
     * (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     */
    public String currencyCode;
    
    /**
     * (�O������������藚�𖾍�)
     * @@roseuid 4289E3DC0278
     */
    public WEB3FeqChangeCancelHistoryGroup() 
    {
     
    }
}
@
