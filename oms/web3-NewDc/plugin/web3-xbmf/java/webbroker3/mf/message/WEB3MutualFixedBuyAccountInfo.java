head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.04.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFixedBuyAccountInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���M�莞��z�����������(WEB3MutualFixedBuyAccountInfo)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/22 ���G�� (���u) �V�K�쐬
*/
package webbroker3.mf.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���M�莞��z�����������)<BR>
 * ���M�莞��z�����������<BR>
 * 
 * @@author ���G��(���u)
 * @@version 1.0 
 */
public class WEB3MutualFixedBuyAccountInfo extends Message
{
    /**
     * (���Z�@@�֋敪)<BR>
     * ���Z�@@�֋敪 <BR>
     * <BR>
     * 1:��s�@@�@@2:�X�֋� <BR>
     */
    public String financialInstitutionDiv;
    
    /**
     * (��s�R�[�h)<BR>
     * ��s�R�[�h <BR>
     * <BR>
     * ��\��<BR>
     */
    public String financialInstitutionCode;
    
    /**
     * (��s��)<BR>
     * ��s�� <BR>
     * <BR>
     * ���Z�@@�֋敪���X�֋ǂ̏ꍇnull<BR>
     */
    public String financialInstitutionName;
    
    /**
     * (�x�X�R�[�h)<BR>
     * �x�X�R�[�h <BR>
     *  <BR>
     * ���Z�@@�֋敪���X�֋ǂ̏ꍇ  <BR>
     * �ʒ��L����2���ڂ���4���ڂƂ��Ďg�p����B  <BR>
     * �i�ʒ��L����1���ځA5���ڂ͌Œ�l���ߕ\���őΉ�����B�j<BR>
     */
    public String financialBranchCode;
    
    /**
     * (�x�X��)<BR>
     * �x�X�� <BR>
     * <BR>
     * ���Z�@@�֋敪���X�֋ǂ̏ꍇnull<BR>
     */
    public String financialBranchName;
    
    /**
     * (�a���敪)<BR>
     * �a���敪 <BR>
     * <BR>
     * 1:���ʁ@@2:���� <BR>
     * ���Z�@@�֋敪���X�֋ǂ̏ꍇnull<BR>
     */
    public String financialAccountDiv;
    
    /**
     * (���������ԍ�)<BR>
     * ���������ԍ� <BR>
     * <BR>
     * ���Z�@@�֋敪���X�֋ǂ̏ꍇ <BR>
     * �ʒ��ԍ���1���ڂ���7���ڂƂ��Ďg�p����B <BR>
     * �i�ʒ��L����8���ڂ͌Œ�l���ߕ\���őΉ�����B�j<BR>
     */
    public String financialAccountCode;
    
    /**
     * (�����������`�l�i�J�i�j)<BR>
     * �����������`�l�i�J�i�j<BR>
     */
    public String financialAccountName;
    
    /**
     * (���M�莞��z�����������̃C���X�^���X�𐶐�����B)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     */
    public WEB3MutualFixedBuyAccountInfo()
    {
    }
}
@
