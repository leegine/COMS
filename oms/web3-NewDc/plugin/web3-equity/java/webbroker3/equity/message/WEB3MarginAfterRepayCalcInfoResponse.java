head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginAfterRepayCalcInfoResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �M�p����ԍό�]�͌v�Z��񃌃X�|���X(WEB3MarginAfterRepayCalcInfoResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/20 �������F (SRA) �V�K�쐬
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.WEB3MarginCloseMarginUpdateInterceptor;
import webbroker3.equity.WEB3MarginSettleContractOrderSpec;

/**
 * �i�M�p����ԍό�]�͌v�Z��񃌃X�|���X�j�B<BR>
 * <BR>
 * �M�p����ԍό�]�͌v�Z��񃌃X�|���X�N���X
 * @@author �������F
 * @@version 1.0
 */
public class WEB3MarginAfterRepayCalcInfoResponse extends WEB3GenResponse
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_after_repay_calc_info";
    
    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200504201900L;
    
    /**
     * (�M�p�ԍϒ������e)<BR>
     * �M�p�ԍϒ������e�I�u�W�F�N�g�B<BR>
     */
    public WEB3MarginSettleContractOrderSpec orderSpec;
    
    /**
     * (�M�p�ԍύX�V�C���^�Z�v�^)<BR>
     * �M�p�ԍύX�V�C���^�Z�v�^�I�u�W�F�N�g�B<BR>
     */
    public WEB3MarginCloseMarginUpdateInterceptor interceptor;
    
    /**
     * (�f�t�H���g�R���X�g���N�^)<BR>
     */
    public WEB3MarginAfterRepayCalcInfoResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)
     */
    public WEB3MarginAfterRepayCalcInfoResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
