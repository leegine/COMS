head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.11.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPOrixTpCalcResultResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �]�͌v�Z���ʃ��X�|���X(WEB3TPOrixTpCalcResultResponse.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/03/18 Matsumoto(SRA) �V�K�쐬
 */
package webbroker3.tradingpower.message;

import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.message.WEB3BackRequest;

/**
 * (�]�͌v�Z���ʃ��X�|���X)<BR>
 * �]�͌v�Z���ʃ��X�|���X�N���X�B<BR>
 * 
 * @@author Matsumoto(SRA)
 */
public class WEB3TPOrixTpCalcResultResponse extends WEB3BackResponse 
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
    * ���b�Z�[�W
    */
   public String message;
   
   /**
    * From����ID
    */
   public long fromAccountID;

   /**
    * To����ID
    */
   public long toAccountID;

   /**
    * �����ڋq�o�͌���
    */
   public long equityRows;

   /**
    * �M�p�ڋq�o�͌���
    */
   public long marginRows;

   /**
    * (�R���X�g���N�^)*
    * @@param l_request
    */
   protected WEB3TPOrixTpCalcResultResponse(WEB3BackRequest l_request) 
   {
       super( l_request );
   }
   
   /**
    * (�R���X�g���N�^)
    */
   public WEB3TPOrixTpCalcResultResponse() 
   {
   }
   
}
@
