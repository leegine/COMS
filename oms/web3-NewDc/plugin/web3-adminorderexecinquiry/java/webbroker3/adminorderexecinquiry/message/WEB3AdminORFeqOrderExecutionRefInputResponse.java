head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.44.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORFeqOrderExecutionRefInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE�O�������������Ɖ���̓��X�|���X(WEB3AdminORFeqOrderExecutionRefInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/22 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.adminorderexecinquiry.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE�O�������������Ɖ���̓��X�|���X)<BR>
 * �Ǘ��ҁE�O�������������Ɖ���̓��X�|���X�N���X<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminORFeqOrderExecutionRefInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_Feq_ORFeqOrderExecutionRefInput";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507130937L;
    
    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �s��R�[�h�̔z��<BR>
     */
    public String[] marketList;
    
    /**
     * (���s�����ꗗ)<BR>
     * ���s�����̔z��<BR>
     */
    public String[] execCondTypeList = null;
    
    /**
     * (���������敪�ꗗ)<BR>
     * ���������敪�̔z��<BR>
     */
    public String[] expirationDateTypeList = null;
    
    /**
     * (���������ꗗ)<BR>
     * ���������̔z��<BR>
     */
    public String[] orderCondTypeList = null;
    
    /**
     * (�����o�H�敪�ꗗ)<BR>
     * �����o�H�敪�̈ꗗ<BR>
     */
    public String[] orderRootDivList;
    
    /**
     * (�������ꗗ)<BR>
     * �������̔z��<BR>
     */
    public Date[] orderBizDateList;
    
    /**
     * (�戵���i�ꗗ)<BR>
     */
    public WEB3AdminORTradingProductUnit[] tradingProductList;
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminORFeqOrderExecutionRefInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
