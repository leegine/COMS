head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityProductSelectResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����������t���������I�����X�|���X(WEB3EquityProductSelectResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 �����a��(SRA) �V�K�쐬
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����������t���������I�����X�|���X�j�B<br>
 * <br>
 * �����������t���������I�������@@���X�|���X�f�[�^�N���X
 * @@author �����a��(SRA) 
 * @@version 1.0
 */
public class WEB3EquityProductSelectResponse extends WEB3GenResponse {

    /**
     * �|�������t�B�b�N�^�C�v<BR>
     */
    public static final String PTYPE = "equity_productSelect";

    /**
     * �V���A���o�[�W����UID<BR>
     */
    public static final long serialVersionUID = 200412100000L;

    /**
     * (�s��R�[�h�ꗗ)<BR>
     * �I���\�s��ꗗ<BR>
     * 1:�����@@2:���@@3:���É��@@6:�����@@8:�D�y�@@9:NNM�@@10:JASDAQ<BR>
     */
    public String[] marketList;
    
    /**
     * (����I���x���s��R�[�h�ꗗ)<BR>
     * ���I���x��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension;
    
    /**
     * (�R���X�g���N�^)<BR>
     * <BR>
     * �����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * <BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3EquityProductSelectResponse(WEB3EquityProductSelectRequest l_request)
    {
        super(l_request);
    }
}
@
