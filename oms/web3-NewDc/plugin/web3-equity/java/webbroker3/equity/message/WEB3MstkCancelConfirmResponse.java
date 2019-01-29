head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        :  �����~�j������������������X�|���X(WEB3MstkCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 �J�N���V (���u) �V�K�쐬
                   2004/12/10 �K�� (SRA) �C��
                   2005/01/05 ���� (SRA) JavaDoc�̏C��
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����~�j������������m�F���X�|���X�j�B<BR>
 * <BR>
 * �����~�j������������m�F���X�|���X�N���X
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3MstkCancelConfirmResponse extends WEB3GenResponse 
{
    
    /**
     * �iPTYPE�j�B
     */
    public final static  String PTYPE = "mstk_cancelConfirm";
        
    /**
     * �iSerialVersionUID�j�B
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * �i�����R�[�h�j�B
     */
    public String productCode;
    
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
     * �i�����敪�j�B<BR>
     * <BR>
     * 1:���t�@@2:���t
     */
    public String dealingType;
    
    /**
     * �i���������j�B
     */
    public String orderQuantity;
    
    /**
     * �i�m�F���������j�B<BR>
     * <BR>
     * ���������s��
     */
    public Date checkDate;
    
    /**
     * �i����I���x���j�B<BR>
     * true�F�x������\������@@<BR>�@@
     * false�F�x������\�����Ȃ�
     */
    public boolean messageSuspensionFlag;
    
    /**
     * �i�����~�j������������m�F���X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
	public WEB3MstkCancelConfirmResponse() 
	{

	} 
    
    /**
     * �i�����~�j������������m�F���X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     * @@param l_request �����~�j������������m�F���N�G�X�g
     */
    public WEB3MstkCancelConfirmResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
