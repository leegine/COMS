head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.02.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryDownloadResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��������ꗗ�t�@@�C���_�E�����[�h���X�|���X(WEB3HistoryTradeHistoryDownloadResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/30 ������(���u) �V�K�쐬
*/

package webbroker3.tradehistory.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (��������ꗗ�t�@@�C���_�E�����[�h���X�|���X)<BR>
 * ��������ꗗ�t�@@�C���_�E�����[�h���X�|���X�N���X<BR>
 * 
 * @@author ������
 * @@version 1.0 
 */
public class WEB3HistoryTradeHistoryDownloadResponse extends WEB3GenResponse 
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "tradeHistory_tradeHistoryDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200511301710L;
         
    /**
     * (�_�E�����[�h�t�@@�C��)<BR>
     * �_�E�����[�h�t�@@�C��<BR>
     * �� CSV�t�@@�C���s�̔z��<BR>
     */
    public String[] downloadFile;
    
    /**
     * (���ݓ���)<BR>
     * ���ݓ���<BR>
     */
    public Date currentDate;
    
    /**
     * @@roseuid 41789C4B03A9
     */
    public WEB3HistoryTradeHistoryDownloadResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3HistoryTradeHistoryDownloadResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
