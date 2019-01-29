head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�������t�����������X�|���X�N���X(WEB3MstkSellCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
                   2004/12/09 �K�� (SAR) �c�Č��Ή� No.281
                   2005/01/05 �����a�� (SRA) JavaDoc�C��
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����~�j�������t�����������X�|���X�j�B<BR>
 * <BR>
 * �����~�j�������t�����������X�|���X�N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellCompleteResponse extends WEB3GenResponse 
{
    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "mstk_sellComplete";

    /**
     * �iSerialVersionUID�j�B
     */
    public static final long serialVersionUID = 200410101059L;    
    /**
     * �i�X�V���ԁj�B
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * �i���ʔԍ��j�B
     */
    public String orderActionId;
    
	/**
	 * �i�C���T�C�_�[�x���\���t���O�j�B<BR>
     * <BR>
	 * true�F�x���\���v<BR>
	 * false�F�x���\���s�v
	 */
	public boolean insiderWarningFlag;
    
    /**
     * �i�����~�j�������t�����������X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     */
	public WEB3MstkSellCompleteResponse() 
	{

	}

    /**
     * �i�����~�j�������t�����������X�|���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     * @@param l_request �����~�j�������t�����������N�G�X�g
     */
    public WEB3MstkSellCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request); 
    }
}
@
