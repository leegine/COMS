head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.41.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPODemandDistributionUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : IPO�\�����z���׃��b�Z�[�W�f�[�^(WEB3IPODemandDistributionUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 ���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * IPO�\�����z���׃��b�Z�[�W�f�[�^�N���X
 *                                                                
 * @@author ����
 * @@version 1.0
 */
public class WEB3IPODemandDistributionUnit extends Message
{
    
    /**
     * �\������
     */
    public String demandNumber;
    
    /**
     * �\���䗦
     */
    public String demandPercentage;
    
    /**
     * �\�����i����
     */
    public String demandPriceLower;
    
    /**
     * �\�����i���
     */
    public String demandPriceUpper;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40E51D28034F
     */
    public WEB3IPODemandDistributionUnit() 
    {
     
    }
}
@
