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
filename	WEB3MstkSellListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3MstkSellListResponse(WEB3MstkSellListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 ���C�g (���u) �V�K�쐬
                   2004/12/10 �K�� (SRA) �C��
*/

package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����~�j�������t�ꗗ���X�|���X�j�B<BR>
 * <BR>
 * �����~�j�������t�ꗗ���X�|���X�N���X
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3MstkSellListResponse extends WEB3GenResponse 
{
    /**
     * �iPTYPE�j�B
     */
    public static final String PTYPE = "mstk_sellList";

    /**
     * �iSerialVersionUID�j�B
     */
    public static final long serialVersionUID = 200410101059L;     
    /**
     * �i�����ꗗ�j�B
     */
    public WEB3MstkProductCodeNameUnit[] productCodeNames;
    
    /**
     * �i���t���׈ꗗ�j�B<BR>
     * <BR>
     * ���������ɕR�t�����������̈ꗗ<BR>
     * �i�����~�j�����ۗL���Y���ׂ̔z��j
     */
    public WEB3MstkSellUnit[] sellList;
    
    /**
     * �i���y�[�W���j<BR>
     * <BR>
     * �Y������S�y�[�W��
     */
    public String totalPages;
    
    /**
     * �i�����R�[�h���j�B<BR>
     * <BR>
     * �Y������S�f�[�^��
     */
    public String totalRecords;
    
    /**
     * �i�\���y�[�W�ԍ��j�B<BR>
     * <BR>
     * ���ۂɕ\������y�[�W�ʒu���w��<BR>
     * ���擪�y�[�W��"1"�Ƃ���
     */
    public String pageIndex;
    
    /**
     * �i����I���x���j�B<BR>
     * <BR>
     * true�F�x������\������@@�@@false�F�x������\�����Ȃ�
     */
    public boolean messageSuspensionFlag;
    
    /**
     * �i�����~�j�������t�ꗗ���X�|���X�N���X�j�B<BR>
     * <BR>
     * �f�t�H���g�R���X�g���N�^
     */
	public WEB3MstkSellListResponse() 
	{

	}
    
    /**
     * �i�����~�j�������t�ꗗ���X�|���X�N���X�j�B<BR>
     * <BR>
     * �R���X�g���N�^
     * @@param l_request �����~�j�������t�ꗗ���N�G�X�g
     */
    public WEB3MstkSellListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
