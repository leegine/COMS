head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputOutputHistoryListResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���o�ɗ������X�|���X(WEB3AioInputOutputHistoryListResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 䈋� (���u) �V�K�쐬
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (���o�ɗ������X�|���X)<BR>
 * ���o�ɗ������X�|���X�N���X
 * @@author 䈋�
 * @@version 1.0
 */
public class WEB3AioInputOutputHistoryListResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_inputOutputHistoryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501271304L;

    /**
     * (�\�����ԁi���j)<BR>
     * �\�����ԁi���j
     */
    public Date displayStartDate;
    
    /**
     * (�\�����ԁi���j)<BR>
     * �\�����ԁi���j
     */
    public Date displayEndDate;
    
    /**
     * (���o�ɗ����ꗗ)<BR>
     * ���o�ɗ��𖾍ׂ̔z��
     */
    public WEB3AioHistoryUnit[] HistoryUnits;
    
    /**
     * (�\���y�[�W�ԍ�)<BR>
     * �\���y�[�W�ԍ�
     */
    public String pageIndex;
    
    /**
     * (���y�[�W��)<BR>
     * ���y�[�W��
     */
    public String totalPages;
    
    /**
     * (�����R�[�h��)<BR>
     * �����R�[�h��
     */
    public String totalRecords;
    
    /**
     * @@roseuid 41EC84F8031C
     */
    public WEB3AioInputOutputHistoryListResponse() 
    {
     
    }
   
    /**
     * @@roseuid 41EC84F8031C
     */
    public WEB3AioInputOutputHistoryListResponse(WEB3GenRequest l_request) 
    {
        super(l_request);        
     
    } 
    
}
@
