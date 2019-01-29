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
filename	WEB3MstkBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�������̓��N�G�X�g(WEB3MstkBuyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���]�� (���u) �V�K�쐬
                   2004/12/13 �����a��(SRA) �c�Č��Ή� �m��.�S�O�V
*/
package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�����~�j�������t�������̓��N�G�X�g�j�B<BR>
 * <BR>
 * �����~�j�������t�������̓��N�G�X�g�N���X
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3MstkBuyInputRequest extends WEB3GenRequest 
{
    /**         
     * ���O�o�̓��[�e�B���e�B      
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBuyInputRequest.class);
    /**
     * PTYPE
     */
    public static final String PTYPE = "mstk_buyInput";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * (�����R�[�h)
     */
    public String productCode;
    
    /**
     * @@roseuid 4167B04D017D
     */
    public WEB3MstkBuyInputRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * <BR>
     * ���������ɂ��̂܂�return����B<BR>
     * @@roseuid 411194020269
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 4167B04D0191
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkBuyInputResponse(this);
    }
}
@
