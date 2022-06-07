import Link from 'next/link';
import React from 'react';

import { Section, SectionText, SectionTitle, SectionSubText } from '../../styles/GlobalComponents';
import Button from '../../styles/GlobalComponents/Button';
import { LeftSection } from './HeroStyles';

const Hero = () => (
  <Section row nopadding>
    <LeftSection>
      <SectionTitle main center>
        Hi,<br/>
        I'm Joseph Evans
      </SectionTitle>
      <SectionText>
        Full Stack Web {'&'} Mobile Application Developer
        {/* from UC Santa Cruz */}
        <SectionSubText>
        University of California, Santa Cruz
        </SectionSubText>
      </SectionText>

      <Button onClick={() => window.location = 'mailTo: Josephev4@gmail.com'}>Learn More</Button>

    </LeftSection>
  </Section>
);

export default Hero;