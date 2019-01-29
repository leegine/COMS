head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3Message.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.stdimpls;

/**
 * WEB3MessageQueue�Ƀv�b�g�����N���X<BR>
 * <BR>
 * ���̃N���X�́A�����T�[�o���z�M���ꂽ���������o�C�g�z��Ƃ���
 * �ێ�����N���X�ł���B
 * ���̃N���X�̂ЂƂ̃C���X�^���X�́A�����T�[�o����x�ɔz�M���ꂽ
 * ��������ێ����Ă���B
 * �����T�[�o���A��x��n���̎������R�[�h��z�M�����ꍇ�A���̃N���X�̃C���X�^���X�́A
 * n���̎������R�[�h���o�C�g�z��Ƃ��ĕێ�����B
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
class WEB3Message
{
    int count; // =0
    byte[] data;

    /**
     * �R���X�g���N�^
     */
    WEB3Message()
    {
        data = new byte[WEB3QuoteConstants.MAX_DATA_SIZE];
    }

    /**
     * �R���X�g���N�^
     * 
     * @@param c �������R�[�h��
     * @@param r ��������ێ�����o�C�g�z��
     */
    WEB3Message(int c, byte[] r)
    {
        this.count = c;
        this.data = r;
    }

    /**
     * �w�肵��WEB3Message���玞�������R�s�[����
     * 
     * @@param src �R�s�[���������
     */
    void copy(WEB3Message src)
    {
        if (src != null)
        {
            this.count = src.count;
            System.arraycopy(
                src.data,
                0,
                this.data,
                0,
                src.count * WEB3QuoteConstants.RECORD_SIZE);
        }
    }

    /**
     * ���ݕێ����Ă��鎞�������p�[�X���A
     * �w�肵��WEB3QuoteEventImpl�Ɏ�������ݒ肷��B
     * 
     * @@param holders ��������ݒ肷��WEB3QuoteEventImpl
     * @@return �ݒ肳�ꂽ�������̃��R�[�h��
     */
    int parse(WEB3QuoteEventImpl[] holders)
    {
        if (holders.length < count)
        {
            throw new Error("Too few holders to parse Message");
        }

        int index = 0;
        int valid = 0;
        for (int i = 0;
            i < count;
            i++, index += WEB3QuoteConstants.RECORD_SIZE)
        {
            if (holders[valid]
                .setData(data, index, WEB3QuoteConstants.RECORD_SIZE))
            {
                valid++;
            }

        }

        return valid;
    }
}
@
