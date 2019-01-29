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
filename	WEB3MstkSellInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�������̓��X�|���X�N���X(WEB3MstkSellInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
                   2004/12/09 �K�� (SRA) �c�Č��Ή� No.281
                   2005/01/05 ���� (SRA) JavaDoc�C��
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����~�j�������t�������̓��X�|���X�j�B<BR>
 * <BR>
 * �����~�j�������t�������̓��X�|���X�N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellInputResponse extends WEB3GenResponse 
{
    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "mstk_sellInput";

    /**
     * �iSerialVersionUID�j�B
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * �i���������j�B
     */
    public String orderQuantity;
    
    /**
     * �i�������j�B
     */
    public String productName;
    
    /**
     * �i�s��R�[�h�j�B<BR>
     * <BR>
     * 1�F���� 2�F��� 3�F���É� 6�F���� 8�F�D�y 9�FNNM 10�FJASDAQ
     */
    public String marketCode;
    
    /**
     * �i����I���x�j�B<BR>
     * true�F�x������\������@@�@@false�F�x������\�����Ȃ�
     */
    public boolean messageSuspensionFlag;
    
	/**
	 * �i�C���T�C�_�[�x���\���t���O�j�B<BR>
	 * true�F�x���\���v<BR>
	 * false�F�x���\���s�v
	 */
	public boolean insiderWarningFlag;
	
    
    /**
     * �i�����~�j�������t�������̓��X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
	public WEB3MstkSellInputResponse() 
	{

	}

    /**
     * �i�����~�j�������t�������̓��X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     * @@param l_request �����~�j�������t�������̓��N�G�X�g
     */
    public WEB3MstkSellInputResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
