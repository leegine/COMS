head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.01.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioOtherCountReferenceInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���̑������Ɖ���̓��X�|���X(WEB3AdminAioOtherCountReferenceInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 ��O��(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (���̑������Ɖ���̓��X�|���X)<BR>
 * ���̑������Ɖ���̓��X�|���X�N���X<BR>
 * �����Ɖ���s���Ώۏ��i�ꗗ�y��<BR>
 * ���i�敪�����Z�@@�֘A�g�̏ꍇ�A���ϋ@@�֏���ԋp����B<BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AdminAioOtherCountReferenceInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "admin_aio_other_count_reference_input";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * (���i�ꗗ) <BR>
     * �Ɖ�Ώۏ��i�̏��i�R�[�h��ԋp����B<BR>
     * <BR>
     * �E���i�R�[�h <BR>
     * 1:�I�����C������ <BR>
     * 2:�ב֕ۏ؋� <BR>
     * 3:�O�������i�O���A�g�j<BR>
     */
    public String[] commodityDivList;
    
    /**
     * (���ϋ@@�ֈꗗ) <BR>
     * ���i�敪�����Z�@@�֘A�g�̏ꍇ�A��g���ϋ@@�֏���ԋp����B<BR>
     */
    public WEB3AioSettleInstitutionUnit[] settleInstitutionUnits;
    
    /**
     * @@roseuid 423552AB00FA
     */
    public WEB3AdminAioOtherCountReferenceInputResponse() 
    {
     
    }
    
    /**
     * (�R���X�g���N�^)<BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AdminAioOtherCountReferenceInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
