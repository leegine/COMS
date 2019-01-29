head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.01.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���v���׏Ɖ�X�|���X(WEB3PLSProfitLossSpecsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 �͌d�� (���u) �V�K�쐬
*/

package webbroker3.tradehistory.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���v���׏Ɖ�X�|���X)<BR>
 * ���v���׏Ɖ�X�|���X�N���X<BR>
 * 
 * @@author �͌d��
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PLSBVS_profitLossSpecs";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411051040L;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��<BR>
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��<BR>
     */
    public String totalRecords;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�<BR>
     */
    public String pageIndex;
    
    /**
     * (���v���׏��ꗗ )<BR>
     */
    public WEB3PLSProfitLossSpecsUnit[] profitLossUnits;
    
    /**
     * @@roseuid 418877BC0186
     */
    public WEB3PLSProfitLossSpecsResponse() 
    {
     
    }
    
    /**
     * �f�t�H���g�R���X�g���N�^<BR>
     * �f�t�H���g�R���X�g���N�^ �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3PLSProfitLossSpecsResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
