head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���v���׏��(WEB3PLSProfitLossSpecsUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.tradehistory.message;

import java.util.Date;
import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (���v���׏��)<BR>
 * ���v���׏��N���X<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsUnit extends Message 
{
    /**
     * (���v����ID)<BR>
     */
    public String profitLossSpecId = null;
    
    /**
     * (��ƔN����)<BR>
     * ��ƔN����<BR>
     * <BR>
     * ����ʔ�\������<BR>
     */
    public Date workDate = null;
    
    /**
     * (���v���׃��R�[�h�敪)<BR>
     * ���v���׃��R�[�h�敪<BR>
     * <BR>
     * 00�F�@@�J�z�c�����R�[�h<BR>
     * 10�F�@@�c�����R�[�h<BR>
     * 20�F�@@���׃��R�[�h<BR>
     * 21�F�@@���o�����R�[�h<BR>
     * <BR>
     * ��00�F�J�z�c�����R�[�h�ɂ��ẮAPR�w�ł̂ݎg�p�B<BR>
     * �@@(DB�ɂ͓o�^����Ȃ��B)<BR>
     */
    public String prolossRecDiv = null;
    
    /**
     * (�v�Z��)<BR>
     * �v�Z��<BR>
     */
    public Date calcDate = null;
    
    /**
     * (��n��)<BR>
     * ��n��<BR>
     */
    public Date deliveryDate = null;
    
    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName = null;
    
    /**
     * (���i)<BR>
     * ���i<BR>
     * <BR>
     * 10�F�@@��������<BR>
     * 11�F�@@�~�j������<BR>
     * 12�F�@@�~�j������<BR>
     * 15�F�@@�M�p���<BR>
     * 30�F�@@������<BR>
     * 40�F�@@�O������<BR>
     * 42�F�@@�O������<BR>
     */
    public String fundType = null;
    
    /**
     * (�K�p�R�[�h)<BR>
     * �K�p�R�[�h<BR>
     * <BR>
     * 10�F�@@�M�p����<BR>
     * 11�F�@@�m��z��<BR>
     * 12�F�@@�a��z��<BR>
     * 13�F�@@�����󕥋�<BR>
     * 20�F�@@�~�j������<BR>
     * 21�F�@@�~�j�[�����p<BR>
     * 22�F�@@�~�j���L������<BR>
     * 31�F�@@�O���������p<BR>
     * ��L�ȊO�F�@@���̑�<BR>
     */
    public String applicationCode = null;
    
    /**
     * (���Z���敪)<BR>
     * ���Z���敪<BR>
     * <BR>
     * 1�F�@@��ʏ��<BR>
     * 2�F�@@����M�p<BR>
     * 3�F�@@�������<BR>
     * 4�F�@@��������<BR>
     */
    public String termDiv = null;
    
    /**
     * (�Ԋҋ��敪)<BR>
     * �Ԋҋ��敪<BR>
     * <BR>
     * 1�F�@@�Ԋҋ�<BR>
     * 1�ȊO�F�@@���̑�<BR>
     */
    public String returnDiv = null;
    
    /**
     * (���l)<BR>
     * ���l<BR>
     * <BR>
     * 1�F�@@�v�Z��<BR>
     * 1�ȊO�F�@@���̑�<BR>
     */
    public String remark = null;
    
    /**
     * (����)<BR>
     * ����<BR>
     */
    public String quantity = null;
    
    /**
     * (���n��)<BR>
     * ���n��<BR>
     */
    public Date passDate = null;
    
    /**
     * (���n���z)<BR>
     * ���n���z<BR>
     */
    public String passAmount = null;
    
    /**
     * (�擾��)<BR>
     * �擾��<BR>
     */
    public Date getDate = null;
    
    /**
     * (�擾�)<BR>
     * �擾�<BR>
     */
    public String getAmount = null;
    
    /**
     * (���v)<BR>
     * ���v<BR>
     */
    public String prolossAmount = null;
    
    /**
     * (�݌v���v)<BR>
     * �݌v���v<BR>
     */
    public String totalProlossAmount = null;
    
    /**
     * (�ېőΏۊz)<BR>
     * �ېőΏۊz<BR>
     */
    public String taxableAmount = null;
    
    /**
     * (�����Ŋz)<BR>
     * �����Ŋz<BR>
     */
    public String collectTaxAmount = null;
    
    /**
     * (�����Ŋz(����))<BR>
     * �����Ŋz(����)<BR>
     */
    public String collectTaxNAmount = null;
    
    /**
     * (�����Ŋz(�n����))<BR>
     * �����Ŋz(�n����)<BR>
     */
    public String collectTaxLAmount = null;
    
    /**
     * (���v���׏��)<BR>
     * �R���X�g���N�^<BR>
     * @@return webbroker3.plsbvs.message.WEB3PLSProfitLossSpecsUnit
     * @@roseuid 416E02580312
     */
    public WEB3PLSProfitLossSpecsUnit() 
    {
     
    }
}
@
