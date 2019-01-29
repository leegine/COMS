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
filename	WEB3MstkBuyConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����m�F���X�|���X(WEB3MstkBuyConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���]�� (���u) �V�K�쐬
                   2004/12/10 �K�� (SRA) �C��
                   2005/01/05 ���� (SRA) JavaDoc�C��
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;


/**
 * �i�����~�j�������t�����m�F���X�|���X�j�B<BR>
 * <BR>
 * �����~�j�������t�����m�F���X�|���X�N���X
 * @@author ���]��
 * @@version 1.0
 */
public class WEB3MstkBuyConfirmResponse extends WEB3MstkConfirmCommonResponse 
{
    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "mstk_buyConfirm";

    /**
     * �iSerialVersionUID�j�B
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * �i�������j�B
     */
    public String productName;
    
    /**
     * �i�s��R�[�h�j�B<BR>
     * <BR>
     * 1�F���� 2�F��� 3�F���É� 6�F���� 8�F�D�y 9�FNNM 10�FJASDAQ<BR>
     */
    public String marketCode;
    
    /**
     * �i�����~�j�������t�����m�F���X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
	public WEB3MstkBuyConfirmResponse() 
	{
	}
    
    /**
     * �i�����~�j�������t�����m�F���X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     * @@param l_request �����~�j�������t�����m�F���N�G�X�g
     */
    public WEB3MstkBuyConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
