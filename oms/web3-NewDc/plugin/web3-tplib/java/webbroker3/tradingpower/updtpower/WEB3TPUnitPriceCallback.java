head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPUnitPriceCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]���P��Callback�C���^�[�t�F�[�X(WEB3TPQuoteInfluenceCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 �V���@@�h�O (FLJ) �V�K�쐬
*/
package webbroker3.tradingpower.updtpower;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;

/**
 * (�]���P��Callback�C���^�[�t�F�[�X)
 */
public interface WEB3TPUnitPriceCallback
{
    /**
     * (get�]���P��<�Ώۖ���>)<BR>
     * �]���P����Ԃ��B(�����N���X�Q��)<BR>
     * @@param l_productRow - (����Row)
     * @@return double
     */
    public double getUnitPrice(ProductRow l_productRow);
    
    /**
     * (get�]���P��<��r>)<BR>
     * �]���P����Ԃ��B(�����N���X�Q��)<BR>
     * @@param l_double - (���l)
     * @@param l_product - (�Ώۖ���)
     * @@return double
     */
    public double getUnitPrice(double l_dblComp, WEB3TPSecurityValuationProduct l_product);
    
    /**
     * (get�]���P��<��r�s�v>)<BR>
     * �]���P����Ԃ��B(�����N���X�Q��)<BR>
     * @@param l_double - (���l)
     * @@param l_product - (�Ώۖ���)
     * @@return double
     */
    public double getUnitPriceNotCompare(double l_dblComp, WEB3TPSecurityValuationProduct l_product);
    
    /**
     * (get�]���P��<����>)<BR>
     * �]���P����Ԃ��B(�����N���X�Q��)<BR>
     * @@param l_targetContractDetail - (�Ώی��ʏڍ�)
     * @@return double
     */
    public double getUnitPrice(WEB3TPTargetContractDetail l_targetContractDetail);
}
@
