head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ReqNumTryOrderMapInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        :�������ʃR�[�h�g���C�I�[�_�}�b�v���N���X(Web3ReqNumTryOrderMapInfo.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 ��(FLJ) �V�K�쐬
 */
package webbroker3.system.tune.affinity;

import java.io.*;

/**
 * �������ʃR�[�h�g���C�I�[�_�}�b�v���
 * **/
public class WEB3ReqNumTryOrderMapInfo
    implements Serializable
{
    private String head2OfOrderRequestNumber;
    private int[] appServerTryOrder;

    public WEB3ReqNumTryOrderMapInfo()
    {

    }

    /**
     * �������ʃR�[�h�g���C�I�[�_�}�b�v���
     *
     * @@param head2OfOrderRequestNumber �������ʃR�[�h���Q��
     * @@param appServerTryOrder APP�T�[�o�g���C�I�[�_
     */
    public WEB3ReqNumTryOrderMapInfo(String head2OfOrderRequestNumber,
                                     int[] appServerTryOrder
                                     )
    {
        this.head2OfOrderRequestNumber = head2OfOrderRequestNumber;
        this.appServerTryOrder = appServerTryOrder;

    }

    /**
     * �������ʃR�[�h���Q����ݒ肷��B
     *
     * @@param head2OfOrderRequestNumber �������ʃR�[�h���Q��
     */
    public void setHead2OfOrderRequestNumber(String head2OfOrderRequestNumber)
    {
        this.head2OfOrderRequestNumber = head2OfOrderRequestNumber;
    }

    /**
     * �������ʃR�[�h���Q�����擾����B
     *
     * @@return �������ʃR�[�h���Q��
     */
    public String getHead2OfOrderRequestNumber()
    {
        return head2OfOrderRequestNumber;
    }

    /**
     * APP�T�[�o�g���C�I�[�_���擾����B
     *
     * @@return �T�[�o�g���C�I�[�_
     */
    public int[] getAppServerTryOrder()
    {
        return appServerTryOrder;
    }

    /**
     * APP�T�[�o�g���C�I�[�_��ݒ肷��B
     *
     * @@param appServerTryOrder �T�[�o�g���C�I�[�_
     */
    public void setAppServerTryOrder(int[] appServerTryOrder)
    {
        this.appServerTryOrder = appServerTryOrder;
    }

}
@
