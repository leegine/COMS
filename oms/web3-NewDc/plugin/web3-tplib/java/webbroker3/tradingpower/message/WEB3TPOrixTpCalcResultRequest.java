head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.48;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrixTpCalcResultRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͌v�Z���ʃ��N�G�X�g�N���X(WEB3TPOrixTpCalcResultRequest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/18 Matsumoto(SRA) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�]�͌v�Z���ʃ��N�G�X�g)<BR>
 * �]�͌v�Z���ʃ��N�G�X�g�N���X�B<BR>
 * 
 * @@author Matsumoto(SRA)
 */
public class WEB3TPOrixTpCalcResultRequest extends WEB3BackRequest 
{

    /**
     * �|�������t�B�b�N�^�C�v�B<BR>
     */
    public static final String PTYPE = "tradingpower_orix_tp_calc_result";

    /**
     * �V���A���o�[�W����UID <BR>
     */
    public static final long serialVersionUId = 200503181330L;
   
    /**
     * From����ID
     */
    public long fromAccountID;

    /**
     * To����ID
     */
    public long toAccountID;

    /**
     * ��ЃR�[�h
     */
    public long institutionCode;

    /**
     * �����敪
     */
    public String procDiv;

    /**
     * (�R���X�g���N�^)
     */
    public WEB3TPOrixTpCalcResultRequest() 
    {
    
    }
   
    /**
     * (create���X�|���X)
     * @@return webbroker3.common.message.WEB3BackResponse
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3TPOrixTpCalcResultResponse(this);
    }
}
@
